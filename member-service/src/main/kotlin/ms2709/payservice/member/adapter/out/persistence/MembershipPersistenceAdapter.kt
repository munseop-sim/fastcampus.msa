package ms2709.payservice.member.adapter.out.persistence

import ms2709.global.PersistenceAdapter
import ms2709.payservice.member.adapter.out.persistence.entity.MembershipJpaEntity
import ms2709.payservice.member.adapter.out.persistence.repository.MembershipJpaEntityRepository
import ms2709.payservice.member.adapter.out.vault.VaultUseCase
import ms2709.payservice.member.application.port.out.FindMembershipPort
import ms2709.payservice.member.application.port.out.ModifyMembershipPort
import ms2709.payservice.member.application.port.out.RegisterMembershipPort
import ms2709.payservice.member.domain.Membership
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull

/**
 *
 * 멤버십 정보를 저장하고 조회하기 위한 PersistenceAdapter
 *
 *  MembershipPersistenceAdapter
 * @author 심문섭
 * @version 1.0
 * @since 2024-04-29 9:28PM
 */
@PersistenceAdapter
class MembershipPersistenceAdapter (
    private val membershipJpaRepository: MembershipJpaEntityRepository,
    private val vaultUseCase: VaultUseCase
): RegisterMembershipPort, FindMembershipPort, ModifyMembershipPort {

    private val log =LoggerFactory.getLogger(MembershipPersistenceAdapter::class.java)

    override fun createMembership(
        name: Membership.MemberName,
        email: Membership.MemberEmail,
        address: Membership.MemberAddress,
        isValid: Membership.MemberIsValid,
        isCorp: Membership.MemberIsCorp
    ): MembershipJpaEntity {
        val saved = membershipJpaRepository.save(
            MembershipJpaEntity(
                name = name.value,
                email = vaultUseCase.encrypt(email.value),
                address = address.value,
                isValid = isValid.value,
                isCorp = isCorp.value,
                refreshToken = ""
            )
        )
        return saved.clone().apply {
            this.email = email.value
        }
    }

    override fun findMembership(membershipId: Membership.MembershipId): MembershipJpaEntity? {
        val findValue = membershipJpaRepository.findByIdOrNull(membershipId.value.toLong())
        return findValue?.let {
            val cloned = it.clone().apply {
                this.email = kotlin.runCatching {
                     vaultUseCase.decrypt(it.email!!)
                }.onFailure {
                    log.error("decrypt email exception -> {}", it.message)
                }.getOrNull() ?: it.email
            }
            cloned
        }
    }

    override fun findMembershipByAddress(addressName: String): List<MembershipJpaEntity> {
        val entityList = membershipJpaRepository.findByAddress(addressName)

        return entityList.map {
            val cloned = it.clone().apply {
                this.email = kotlin.runCatching {
                    vaultUseCase.decrypt(it.email!!)
                }.onFailure {
                    log.error("decrypt email exception -> {}", it.message)
                }.getOrNull() ?: it.email
            }
            cloned
        }
    }

    override fun modifyMembership(
        membershipId: Membership.MembershipId,
        memberName: Membership.MemberName,
        memberEmail: Membership.MemberEmail,
        memberAddress: Membership.MemberAddress,
        memberIsValid: Membership.MemberIsValid,
        memberIsCorp: Membership.MemberIsCorp
    ) :MembershipJpaEntity{
        val entity = membershipJpaRepository.findByIdOrNull(membershipId.value.toLong())!!
        entity.name = memberName.value
        entity.email = vaultUseCase.encrypt(memberEmail.value)
        entity.address = memberAddress.value
        entity.isValid = memberIsValid.value
        entity.isCorp = memberIsCorp.value
        val saved =  membershipJpaRepository.save(entity)
        return saved.clone().apply {
            this.email = memberEmail.value
        }
    }

    override fun modifyRefreshToken(membershipId: Membership.MembershipId, refreshToken: String): MembershipJpaEntity {
        val entity = membershipJpaRepository.findByIdOrNull(membershipId.value.toLong())!!
        entity.refreshToken = refreshToken
        return membershipJpaRepository.save(entity)
    }
}