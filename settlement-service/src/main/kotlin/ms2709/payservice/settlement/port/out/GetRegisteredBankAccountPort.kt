package ms2709.payservice.settlement.port.out

import ms2709.payservice.settlement.port.out.dto.RegisteredBankAccountAggregateIdentifier

/**
 *
 * 클래스 설명
 *
 * @class GetRegisteredBankAccountPort
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-18 오후 10:14
 */
interface GetRegisteredBankAccountPort {
    fun getRegisteredBankAccount(membershipId:String): RegisteredBankAccountAggregateIdentifier?

    // 타겟 계좌, 금액
    fun requestFirmbanking( bankName:String,  bankAccountNumber:String, moneyAmount:Int)
}