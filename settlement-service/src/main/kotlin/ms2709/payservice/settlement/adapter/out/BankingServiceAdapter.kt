package ms2709.payservice.settlement.adapter.out

import com.fasterxml.jackson.databind.ObjectMapper
import ms2709.global.ExternalSystemAdapter
import ms2709.global.GlobalHttpClient
import ms2709.payservice.settlement.adapter.out.dto.RequestFirmbankingRequest
import ms2709.payservice.settlement.port.out.GetRegisteredBankAccountPort
import ms2709.payservice.settlement.port.out.dto.RegisteredBankAccountAggregateIdentifier
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value

/**
 *
 * 클래스 설명
 *
 * @class BankingServiceAdapter
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-18 오후 10:24
 */
@ExternalSystemAdapter
class BankingServiceAdapter(
    private val globalHttpClient: GlobalHttpClient,
    @Value("\${service.banking.url}") private val bankingServiceUrl: String,
    private val objectMapper: ObjectMapper
): GetRegisteredBankAccountPort {

    private val log = LoggerFactory.getLogger(BankingServiceAdapter::class.java)

    override fun getRegisteredBankAccount(membershipId: String): RegisteredBankAccountAggregateIdentifier? {
        val serviceUrl = "$bankingServiceUrl/banking/account/$membershipId"
        val response = globalHttpClient.sendGetRequest(serviceUrl).body()
        return kotlin.runCatching {
            objectMapper.readValue(response, RegisteredBankAccountAggregateIdentifier::class.java)
        }.onFailure {
            log.error("Failed to get registered bank account. membershipId: $membershipId", it)
        }.getOrNull()

    }

    override fun requestFirmbanking(bankName: String, bankAccountNumber: String, moneyAmount: Int) {
        val serviceUrl = "$bankingServiceUrl/banking/firmbanking/request-eda"
        val requestParam = RequestFirmbankingRequest().apply {
            this.fromBankName = "MS2709"
            this.fromBankAccountNumber = "1234567890"
            this.toBankName = bankName
            this.toBankAccountNumber = bankAccountNumber
            this.moneyAmount = moneyAmount
        }
        val response = globalHttpClient.sendPostRequest(serviceUrl, objectMapper.writeValueAsString(requestParam)).body()
        log.info("Response from banking service: $response")
    }
}