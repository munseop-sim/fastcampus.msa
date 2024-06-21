package ms2709.payservice.member.adapter.`in`.web

import ms2709.payservice.member.application.port.`in`.RefreshTokenCommand

/**
 *
 * 클래스 설명
 *
 * @class RefreshTokenRequest
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-21 오후 11:17
 */
class RefreshTokenRequest {
    var refreshToken: String? = null
    fun toCommand(): RefreshTokenCommand {
        return RefreshTokenCommand(
            refreshToken = refreshToken!!
        )
    }
}