package ms2709.member.adapter.`in`.web

/**
 *
 * 클래스 설명
 *
 * @class ModifyMembershipRequest
 * @author 심문섭
 * @version 1.0
 * @since 2024-05-08 오전 8:19
 */
class ModifyMembershipRequest {
    var membershipId: String? = null
    var name: String? = null
    var address:String? = null
    var email:String? = null

    var isValid:Boolean? = null
    var isCorp:Boolean? = null
}