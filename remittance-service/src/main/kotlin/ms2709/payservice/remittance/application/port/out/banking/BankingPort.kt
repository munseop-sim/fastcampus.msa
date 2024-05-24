package ms2709.payservice.remittance.application.port.out.banking

/**
 *
 * 클래스 설명
 *
 * @class BankingPort
 * @author 심문섭
 * @version 1.0
 * @since 2024-05-25 오전 7:45
 */
interface BankingPort {
    fun getMembershipBankingInfo(bankName: String, bankAccountNumber: String): BankingInfo

    fun requestFirmbanking(bankName: String, bankAccountNumber: String, amount: Int): Boolean

}