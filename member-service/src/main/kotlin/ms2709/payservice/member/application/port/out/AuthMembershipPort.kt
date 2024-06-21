package ms2709.payservice.member.application.port.out

import ms2709.payservice.member.domain.Membership

/**
 *
 * 클래스 설명
 *
 * @class AuthMembershipPort
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-21 오후 11:29
 */
interface AuthMembershipPort {
    fun generateAccessToken( membershipId: Membership.MembershipId): String
    fun generateRefreshToken(membershipId: Membership.MembershipId): String
    fun validateToken(token: String): Boolean
    fun getMembershipId(token: String): Membership.MembershipId
}