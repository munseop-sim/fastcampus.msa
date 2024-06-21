package ms2709.payservice.member.application.port.`in`

/**
 *
 * 클래스 설명
 *
 * @class GetMembershipByTokenCommand
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-21 오후 11:23
 */
data class GetMembershipByTokenCommand (
    val accessToken: String
)