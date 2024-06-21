package ms2709.payservice.member.application.port.`in`

import ms2709.payservice.member.domain.JwtToken
import ms2709.payservice.member.domain.Membership

/**
 *
 * 클래스 설명
 *
 * @class AuthMembershipUseCase
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-21 오후 11:10
 */
interface AuthMembershipUseCase {
    fun loginMembership(command: LoginMembershipCommand): JwtToken
    fun refreshToken(command: RefreshTokenCommand): JwtToken
    fun validateToken(toCommand: ValidateTokenCommand): Boolean
    fun getMembershipByToken(command: GetMembershipByTokenCommand): Membership
}