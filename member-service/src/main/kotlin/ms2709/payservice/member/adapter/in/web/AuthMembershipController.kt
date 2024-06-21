package ms2709.payservice.member.adapter.`in`.web

import ms2709.global.WebAdapter
import ms2709.payservice.member.application.port.`in`.AuthMembershipUseCase
import ms2709.payservice.member.domain.JwtToken
import ms2709.payservice.member.domain.Membership
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 *
 * 클래스 설명
 *
 * @class AuthMembershipController
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-21 오후 10:18
 */
@WebAdapter
@RestController
@RequestMapping("/membership")
class AuthMembershipController (
    private val authMembershipUseCase: AuthMembershipUseCase
){
    @PostMapping("/login")
    fun loginMembership(@RequestBody loginMembershipRequest:LoginMembershipRequest):JwtToken{
        return authMembershipUseCase.loginMembership(loginMembershipRequest.toCommand())
    }

    @PostMapping("/refresh-token")
    fun refreshToken(@RequestBody refreshTokenRequest: RefreshTokenRequest):JwtToken {
        return authMembershipUseCase.refreshToken(refreshTokenRequest.toCommand())
    }

    @PostMapping("/validate-token")
    fun validateToken(@RequestBody validateTokenRequest: ValidateTokenRequest):Boolean {
        return authMembershipUseCase.validateToken(validateTokenRequest.toCommand())
    }

    @PostMapping("/token-membership")
    fun getMembershipByToken(@RequestBody getMembershipByTokenRequest: GetMembershipByTokenRequest): Membership {
        return authMembershipUseCase.getMembershipByToken(getMembershipByTokenRequest.toCommand())
    }
}