package ms2709.member.domain

/**
 * Membership 도메인 클래스
 *
 * @class Membership
 * @author 심문섭
 * @version 1.0
 * @since 2024-04-29 8:42 AM
 */
class Membership {
    val membershipId:String
    val name:String
    val email:String
    val address:String
    val isValid:Boolean
    val isCorp:Boolean

    private constructor(membershipId: String, name: String, email: String, address: String, isValid: Boolean, isCorp: Boolean) {
        this.membershipId = membershipId
        this.name = name
        this.email = email
        this.address = address
        this.isValid = isValid
        this.isCorp = isCorp
    }


    companion object {
        fun create(membershipId: MembershipId,
                   name: MemberName,
                   email: MemberEmail,
                   address: MemberAddress,
                   isValid: MemberIsValid,
                   isCorp: MemberIsCorp
        ): Membership {
            return Membership(
                membershipId.value,
                name.value,
                email.value,
                address.value,
                isValid.value,
                isCorp.value
            )
        }
    }

    class MembershipId(val value: String) {
        init {
            require(value.isNotEmpty()) { "membershipId must not be empty" }
        }
    }
    class MemberName(val value: String) {
        init {
            require(value.isNotEmpty()) { "name must not be empty" }
        }
    }

    class MemberEmail(val value: String) {
        init {
            require(value.isNotEmpty()) { "email must not be empty" }
        }
    }

    class MemberAddress(val value: String) {
        init {
            require(value.isNotEmpty()) { "address must not be empty" }
        }
    }

    class MemberIsValid(val value: Boolean) {
        init {
            require(value) { "isValid must be true" }
        }
    }

    class MemberIsCorp(val value: Boolean) {}
}