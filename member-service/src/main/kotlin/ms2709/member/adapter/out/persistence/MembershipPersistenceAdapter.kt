package ms2709.member.adapter.out.persistence

import ms2709.global.PersistenceAdapter
import ms2709.member.adapter.out.persistence.entity.MembershipJpaEntity
import ms2709.member.adapter.out.persistence.repository.MembershipJpaEntityRepository
import ms2709.member.application.port.out.FindMembershipPort
import ms2709.member.application.port.out.ModifyMembershipPort
import ms2709.member.application.port.out.RegisterMembershipPort
import ms2709.member.domain.Membership
import org.springframework.data.repository.findByIdOrNull

/**
 *
 * 멤버십 정보를 저장하고 조회하기 위한 PersistenceAdapter
 *
 * @class MembershipPersistenceAdapter
 * @author 심문섭
 * @version 1.0
 * @since 2024-04-29 9:28PM
 */
@PersistenceAdapter
class MembershipPersistenceAdapter (
    private val membershipJpaRepository: MembershipJpaEntityRepository
): RegisterMembershipPort, FindMembershipPort, ModifyMembershipPort {
    override fun createMembership(
        name: Membership.MemberName,
        email: Membership.MemberEmail,
        address: Membership.MemberAddress,
        isValid: Membership.MemberIsValid,
        isCorp: Membership.MemberIsCorp
    ): MembershipJpaEntity {
        return membershipJpaRepository.save(
            MembershipJpaEntity(
                name = name.value,
                email = email.value,
                address = address.value,
                isValid = isValid.value,
                isCorp = isCorp.value
            )
        )
    }

    override fun findMembership(membershipId: Membership.MembershipId): MembershipJpaEntity? {
        return membershipJpaRepository.findByIdOrNull(membershipId.value.toLong())
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
        entity.email = memberEmail.value
        entity.address = memberAddress.value
        entity.isValid = memberIsValid.value
        entity.isCorp = memberIsCorp.value
        return membershipJpaRepository.save(entity)
    }
}