package ms2709.payservice.member.adapter.`in`.web

import ms2709.payservice.member.application.port.`in`.ValidateTokenCommand

class ValidateTokenRequest {
    var accessToken: String? = null
    fun toCommand(): ValidateTokenCommand {
        return ValidateTokenCommand(
            accessToken = accessToken!!
        )
    }
}
