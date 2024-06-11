package ms2709.payservice.payment.adapter.out.service

import com.fasterxml.jackson.databind.ObjectMapper
import ms2709.global.GlobalHttpClient
import ms2709.payservice.payment.application.port.out.GetRegisteredBankAccountPort
import ms2709.payservice.payment.application.port.out.RegisteredBankAccountAggregateIdentifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

/**
 *
 * 클래스 설명
 *
 *  BankingServiceAdapter
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-03 오전 12:01
 */
@Component
class BankingServiceAdapter (
    private val globalHttpClient: GlobalHttpClient,
    @Value("\${service.banking.url}") private val bankingServiceUrl: String,
    private val objectMapper: ObjectMapper
):GetRegisteredBankAccountPort{
    override fun getRegisteredBankAccount(memberId: String): RegisteredBankAccountAggregateIdentifier {
        val url= "$bankingServiceUrl/banking/account/$memberId"
        val response = globalHttpClient.sendGetRequest(url).body()
        return objectMapper.readValue(response, RegisteredBankAccount::class.java).run {
            RegisteredBankAccountAggregateIdentifier(
                this.registeredBankAccountId ?: "",
                this.aggregateIdentifier ?: "",
                this.membershipId ?: "",
                this.bankAccountNumber ?: "",
                this.bankName ?: "")
        }
    }
}