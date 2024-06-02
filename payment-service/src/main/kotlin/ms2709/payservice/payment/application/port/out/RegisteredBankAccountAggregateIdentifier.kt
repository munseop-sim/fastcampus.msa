package ms2709.payservice.payment.application.port.out

/**
 *
 * 클래스 설명
 *
 * @class RegisteredBankAccountAggregateIdentifier
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-02 오후 11:28
 */
class RegisteredBankAccountAggregateIdentifier {

    var registeredBankAccountId:String? = null
    var aggregateIdentifier:String? = null
    var membershipId:String? =null
    var bankAccountNumber:String? = null
    var bankName:String? =null

    constructor(
        registeredBankAccountId: String?,
        aggregateIdentifier: String?,
        membershipId: String?,
        bankAccountNumber: String?,
        bankName: String?
    ) {
        this.registeredBankAccountId = registeredBankAccountId
        this.aggregateIdentifier = aggregateIdentifier
        this.membershipId = membershipId
        this.bankAccountNumber = bankAccountNumber
        this.bankName = bankName
    }


    override fun toString(): String {
        return "RegisteredBankAccountAggregateIdentifier(registeredBankAccountId=$registeredBankAccountId, aggregateIdentifier=$aggregateIdentifier, membershipId=$membershipId, bankAccountNumber=$bankAccountNumber, bankName=$bankName)"
    }


}