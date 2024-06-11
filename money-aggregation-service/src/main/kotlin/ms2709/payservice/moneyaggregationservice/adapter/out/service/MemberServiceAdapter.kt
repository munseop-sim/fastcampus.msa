package ms2709.payservice.moneyaggregationservice.adapter.out.service

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import ms2709.global.GlobalHttpClient
import ms2709.payservice.moneyaggregationservice.application.port.out.GetMembershipPort
import org.apache.logging.log4j.util.Strings
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

/**
 *
 * 클래스 설명
 *
 *  MemberServiceAdapter
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-11 오후 12:56
 */
@Component
class MemberServiceAdapter(
    private val globalHttpClient: GlobalHttpClient,
    @Value("\${service.membership.url}") private val serviceMembershipUrl: String,
    private val objectMapper: ObjectMapper
) : GetMembershipPort {

    override fun getMembershipByAddress(address: String): List<String> {
        val url = "${serviceMembershipUrl}/membership/find/address/${address}"

        return globalHttpClient.sendGetRequest(url).body().let {
            return if (Strings.isNotBlank(it)) {
                objectMapper.readValue(it,object: TypeReference<List<Membership>>(){}).mapNotNull {membership-> membership.membershipId }
            } else {
                emptyList()
            }
        }
    }
}
