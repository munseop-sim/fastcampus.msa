package ms2709.payservice.member.application.port.`in`

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import ms2709.global.SelfValidating

/**
 *
 * 멤버십 조회를 위한 커맨드
 *
 * @class FindMembershipCommand
 * @author 심문섭
 * @version 1.0
 * @since 2024-04-29 10:41 PM
 */
class FindMembershipCommand: SelfValidating<FindMembershipCommand> {
    @NotNull
    @NotBlank
    val membershipId:String

    constructor(membershipId: String) {
        this.membershipId = membershipId
        this.validateSelf()
    }


}