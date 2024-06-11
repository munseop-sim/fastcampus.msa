package ms2709.payservice.moneyaggregationservice.adapter.out.service

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import ms2709.global.GlobalHttpClient
import ms2709.payservice.moneyaggregationservice.application.port.out.GetMoneySumPort
import ms2709.payservice.moneyaggregationservice.application.port.out.MemberMoney
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

/**
 *
 * 클래스 설명
 *
 * @class MoneyServiceAdapter
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-11 오후 1:14
 */
@Component
class MoneyServiceAdapter(
    private val globalHttpClient: GlobalHttpClient,
    @Value("\${service.money.url}") private val serviceMoneyUrl: String,
    private val objectMapper: ObjectMapper
): GetMoneySumPort {
    override fun getMoneySumByMembershipIdList(membershipIdList: List<String>): List<MemberMoney> {
        val url = "${serviceMoneyUrl}/money/find/member-money"

        FindMemberMoneyRequest().apply {
            this.membershipIdList = membershipIdList
        }.run {
            globalHttpClient.sendPostRequest(url,objectMapper.writeValueAsString(this))
        }.run{
            this.body()
        }.run {
            return if (isNotBlank()) {
                objectMapper.readValue(this, object: TypeReference<List<MemberMoney>>(){}).mapNotNull { memberMoney -> memberMoney }
            } else {
                emptyList()
            }
        }

    }
}