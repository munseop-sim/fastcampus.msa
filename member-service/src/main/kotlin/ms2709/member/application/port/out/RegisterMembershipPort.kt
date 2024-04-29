package ms2709.member.application.port.out

import ms2709.member.adapter.out.persistence.entity.MembershipJpaEntity
import ms2709.member.domain.Membership

/**
 *
 * 멤버십 등록을 위한 포트
 *
 * @class RegisterMembershipPort
 * @author 심문섭
 * @version 1.0
 * @since 2024-04-29 10:18 PM
 */
interface RegisterMembershipPort {
    fun createMembership(
        name: Membership.MemberName,
        email: Membership.MemberEmail,
        address: Membership.MemberAddress,
        isValid: Membership.MemberIsValid,
        isCorp: Membership.MemberIsCorp
    ):MembershipJpaEntity
}