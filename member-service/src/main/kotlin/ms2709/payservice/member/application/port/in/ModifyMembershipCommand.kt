package ms2709.payservice.member.application.port.`in`

import jakarta.validation.constraints.AssertTrue
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import ms2709.global.SelfValidating

/**
 *
 * 클래스 설명
 *
 *  ModifyMembershipCommand
 * @author 심문섭
 * @version 1.0
 * @since 2024-05-08 오전 8:20
 */
class ModifyMembershipCommand : SelfValidating<ModifyMembershipCommand> {

    @NotNull
    var membershipId: String? = null
    @NotNull
    var name: String? = null
    @NotNull
    @NotBlank
    var address:String? = null
    @NotNull
    var email:String? = null
    @AssertTrue
    var isValid:Boolean? = null

    var isCorp:Boolean? = null

    constructor(
        membershipId: String?,
        name: String?,
        address: String?,
        email: String?,
        isValid: Boolean?,
        isCorp: Boolean?
    ) : super() {
        this.membershipId = membershipId
        this.name = name
        this.address = address
        this.email = email
        this.isValid = isValid
        this.isCorp = isCorp

        this.validateSelf()
    }
}