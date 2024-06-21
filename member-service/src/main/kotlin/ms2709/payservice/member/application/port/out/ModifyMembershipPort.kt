package ms2709.payservice.member.application.port.out

import ms2709.payservice.member.adapter.out.persistence.entity.MembershipJpaEntity
import ms2709.payservice.member.domain.Membership

/**
 *
 * 클래스 설명
 *
 *  ModifyMembershipPort
 * @author 심문섭
 * @version 1.0
 * @since 2024-05-08 오전 8:20
 */
interface ModifyMembershipPort {
    fun modifyMembership(
        membershipId:Membership.MembershipId,
        memberName:Membership.MemberName,
        memberEmail: Membership.MemberEmail,
        memberAddress: Membership.MemberAddress,
        memberIsValid: Membership.MemberIsValid,
        memberIsCorp: Membership.MemberIsCorp
    ):MembershipJpaEntity

    fun modifyRefreshToken(
        membershipId:Membership.MembershipId,
        refreshToken:String
    ):MembershipJpaEntity
}