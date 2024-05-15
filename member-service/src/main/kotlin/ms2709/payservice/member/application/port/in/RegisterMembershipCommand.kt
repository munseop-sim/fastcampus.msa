package ms2709.payservice.member.application.port.`in`

import jakarta.validation.constraints.AssertTrue
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import ms2709.global.SelfValidating

/**
 *
 * 멤버십 등록을 위한 커맨드
 *
 * @class RegisterMembershipCommand
 * @author 심문섭
 * @version 1.0
 * @since 2024-04-29 10:09PM
 */
class RegisterMembershipCommand: SelfValidating<RegisterMembershipCommand> {

    @NotNull
    val name: String

    @NotNull
    val email: String

    @NotNull
    @NotBlank
    val address: String

    @NotNull
    val isCorp: Boolean

    @AssertTrue
    val isValid: Boolean

    constructor(name: String, email: String, address: String, isCorp: Boolean, isValid:Boolean) {
        this.name = name
        this.email = email
        this.address = address
        this.isCorp = isCorp
        this.isValid = isValid
        this.validateSelf()
    }
}