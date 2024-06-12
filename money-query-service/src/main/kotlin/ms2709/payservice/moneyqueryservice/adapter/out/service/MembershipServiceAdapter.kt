package ms2709.payservice.moneyqueryservice.adapter.out.service

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import ms2709.global.GlobalHttpClient
import ms2709.payservice.moneyqueryservice.application.port.out.GetMemberAddressInfoPort
import ms2709.payservice.moneyqueryservice.application.port.out.MemberAddressInfo
import org.apache.logging.log4j.util.Strings
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

/**
 *
 * 클래스 설명
 *
 * @class MembershipServiceAdapter
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-12 오전 10:54
 */
@Component
class MembershipServiceAdapter(
    private val globalHttpClient: GlobalHttpClient,
    @Value("\${service.membership.url}") private val serviceMembershipUrl: String,
    private val objectMapper: ObjectMapper
) : GetMemberAddressInfoPort {
    override fun getMemberAddressInfo(membershipId: String): MemberAddressInfo? {
        val url = "${serviceMembershipUrl}/membership/find/${membershipId}"

        return globalHttpClient.sendGetRequest(url).body().let {
            if (Strings.isNotBlank(it)) {
                objectMapper.readValue(it, Membership::class.java)
            }else{
                null
            }
        }?.let {
            MemberAddressInfo(it.membershipId, it.address)
        }
    }
}