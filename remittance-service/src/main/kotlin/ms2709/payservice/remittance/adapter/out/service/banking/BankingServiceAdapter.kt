package ms2709.payservice.remittance.adapter.out.service.banking

import ms2709.global.ExternalSystemAdapter
import ms2709.global.GlobalHttpClient
import ms2709.payservice.remittance.application.port.out.banking.BankingInfo
import ms2709.payservice.remittance.application.port.out.banking.BankingPort
import org.springframework.beans.factory.annotation.Value

/**
 *
 * 클래스 설명
 *
 *  BankingServiceAdapter
 * @author 심문섭
 * @version 1.0
 * @since 2024-05-25 오전 8:21
 */
@ExternalSystemAdapter
class BankingServiceAdapter(
    private val globalHttpClient: GlobalHttpClient
) : BankingPort {

    @Value("\${service.banking.url}")
    lateinit var bankingServiceEndpoint: String

    override fun getMembershipBankingInfo(bankName: String, bankAccountNumber: String): BankingInfo {
        //todo - banking-service의 계좌정보를 조회하는 API 호출 구현(올바른 계좌인지 체크하는 로직 필요)
        return BankingInfo(bankName, bankAccountNumber, true)
    }

    override fun requestFirmbanking(bankName: String, bankAccountNumber: String, amount: Int): Boolean {
        //todo - banking-service의 계좌이체 API 호출 구현
        return true
    }
}