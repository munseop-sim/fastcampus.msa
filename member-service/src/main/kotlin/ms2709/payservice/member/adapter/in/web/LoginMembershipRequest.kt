package ms2709.payservice.member.adapter.`in`.web

import ms2709.payservice.member.application.port.`in`.LoginMembershipCommand

/**
 *
 * 클래스 설명
 *
 * @class LoginMembershipRequest
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-21 오후 11:14
 */
class LoginMembershipRequest {
    var membershipId: String? = null

    fun toCommand():LoginMembershipCommand{
        return LoginMembershipCommand(
            membershipId = membershipId!!
        )
    }
}