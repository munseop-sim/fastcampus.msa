package ms2709.payservice.settlement.port.out.dto

/**
 *
 * 클래스 설명
 *
 * @class RegisteredBankAccountAggregateIdentifier
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-18 오후 10:15
 */
class RegisteredBankAccountAggregateIdentifier {
    var registeredBankAccountId:String? = null
    var aggregateIdentifier: String? = null
    var membershipId:String? = null
    var bankName:String? = null
    var bankAccountNumber:String? =null
}