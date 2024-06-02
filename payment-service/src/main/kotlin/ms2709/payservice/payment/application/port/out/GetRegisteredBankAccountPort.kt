package ms2709.payservice.payment.application.port.out

/**
 *
 * 클래스 설명
 *
 * @class CreatePaymentPort
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-02 오후 11:25
 */
interface GetRegisteredBankAccountPort {
    fun getRegisteredBankAccount(memberId: String): RegisteredBankAccountAggregateIdentifier
}