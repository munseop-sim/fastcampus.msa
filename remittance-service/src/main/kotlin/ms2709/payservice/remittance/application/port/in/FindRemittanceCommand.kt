package ms2709.payservice.remittance.application.port.`in`

import jakarta.validation.constraints.NotNull
import ms2709.global.SelfValidating

class FindRemittanceCommand : SelfValidating<FindRemittanceCommand>{
    @NotNull
    private var membershipId:String

    fun getMembershipId(): String {
        return membershipId
    }

    constructor(membershipId: String) : super() {
        this.membershipId = membershipId
        this.validateSelf()
    }
}
