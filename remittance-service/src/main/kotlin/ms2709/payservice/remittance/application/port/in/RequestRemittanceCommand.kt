package ms2709.payservice.remittance.application.port.`in`

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import ms2709.global.SelfValidating

/**
 *
 * 클래스 설명
 *
 * @class RequestRemittanceCommand
 * @author 심문섭
 * @version 1.0
 * @since 2024-05-25 오전 6:46
 */
class RequestRemittanceCommand: SelfValidating<RequestRemittanceCommand> {
    @NotNull
    val fromMembershipId:String
    val toMembershipId:String
    val toBankName:String
    val toBankAccountNumber:String
    val remittanceType:Int //0: membership(내부고객), 1: bank(외부은행계좌)
    @NotNull
    @Positive
    val amount:Int //송금요청 금액

    constructor(
        fromMembershipId: String,
        toMembershipId: String,
        toBankName: String,
        toBankAccountNumber: String,
        remittanceType: Int,
        amount: Int
    ) : super() {
        this.fromMembershipId = fromMembershipId
        this.toMembershipId = toMembershipId
        this.toBankName = toBankName
        this.toBankAccountNumber = toBankAccountNumber
        this.remittanceType = remittanceType
        this.amount = amount
        this.validateSelf()
    }
}