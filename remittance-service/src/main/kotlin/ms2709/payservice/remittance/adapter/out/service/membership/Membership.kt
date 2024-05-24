package ms2709.payservice.remittance.adapter.out.service.membership

/**
 *
 * 클래스 설명
 *
 * @class Membership
 * @author 심문섭
 * @version 1.0
 * @since 2024-05-25 오전 7:36
 */
class Membership {
    var membershipId: String? = null
    var name: String? = null
    var email: String? = null
    var address: String? = null
    var isValid = false
    var isCorp = false

    override fun toString(): String {
        return "Membership(membershipId=$membershipId, name=$name, email=$email, address=$address, isValid=$isValid, isCorp=$isCorp)"
    }

}