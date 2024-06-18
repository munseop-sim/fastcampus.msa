package ms2709.payservice.settlement.adapter.out

import com.fasterxml.jackson.databind.ObjectMapper
import ms2709.global.ExternalSystemAdapter
import ms2709.global.GlobalHttpClient
import ms2709.payservice.settlement.port.out.PaymentPort
import ms2709.payservice.settlement.port.out.dto.Payment
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 *
 * 클래스 설명
 *
 * @class PaymentServiceAdapter
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-18 오후 10:24
 */
@ExternalSystemAdapter
class PaymentServiceAdapter(
    private val globalHttpClient: GlobalHttpClient,
    @Value("\${service.payment.url}") private val paymentServiceUrl: String,
    private val objectMapper: ObjectMapper
): PaymentPort {

    private val log = LoggerFactory.getLogger(PaymentServiceAdapter::class.java)

    override fun getNormalStatusPayments(startDate:LocalDate, endDate: LocalDate): List<Payment> {
        val serviceUrl = "$paymentServiceUrl/payment/normal-status?startDate=${startDate.format(DateTimeFormatter.ISO_DATE)}&endDate=${endDate.format(DateTimeFormatter.ISO_DATE)}"
        return runCatching{
            globalHttpClient.sendGetRequest(serviceUrl).body().run {
                objectMapper.readValue(this, Array<Payment>::class.java).toList()
            }
        }.onFailure {
            log.error("Failed to get normal status payments", it)
        }.getOrNull() ?: emptyList()
    }

    override fun finishSettlement(paymentId: Long) {
        val serviceUrl = "$paymentServiceUrl/payment/finish-settlement"
        runCatching {
            val request  = globalHttpClient.sendPostRequest(serviceUrl, objectMapper.writeValueAsString(mapOf("paymentId" to paymentId.toString())))
            log.info("Finish settlement response: ${request.body()}")
        }.onFailure {
            log.error("Failed to finish settlement", it)
        }
    }

}