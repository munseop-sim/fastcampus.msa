package ms2709.member.adapter.out.persistence

import ms2709.global.PersistenceAdapter
import ms2709.member.adapter.out.persistence.entity.MembershipJpaEntity
import ms2709.member.adapter.out.persistence.repository.MembershipJpaEntityRepository
import ms2709.member.application.port.out.FindMembershipPort
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
): RegisterMembershipPort, FindMembershipPort {
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
}