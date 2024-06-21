package ms2709.payservice.member.adapter.`in`.web

import ms2709.payservice.member.application.port.`in`.GetMembershipByTokenCommand

class GetMembershipByTokenRequest {
    var accessToken:String? = null

    fun toCommand(): GetMembershipByTokenCommand {
        return GetMembershipByTokenCommand(
            accessToken = accessToken!!
        )
    }
}
