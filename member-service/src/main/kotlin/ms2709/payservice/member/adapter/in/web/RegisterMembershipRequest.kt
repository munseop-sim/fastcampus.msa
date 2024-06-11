package ms2709.payservice.member.adapter.`in`.web

/**
 *
 * 멤버십 등록을 위한 웹요청 파라미터 타입
 *
 *  RegisterMembershipRequest
 * @author 심문섭
 * @version 1.0
 * @since 2024-04-29 9:24AM
 */
data class RegisterMembershipRequest (
    val name: String,
    val email: String,
    val address: String,
    val isCorp: Boolean
)