package ms2709.payservice.payment.adapter.out.service

import com.fasterxml.jackson.databind.ObjectMapper
import ms2709.global.GlobalHttpClient
import ms2709.payservice.payment.application.port.out.GetMembershipPort
import ms2709.payservice.payment.application.port.out.MembershipStatus
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

/**
 *
 * 클래스 설명
 *
 *  MembershipServiceAdapter
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-02 오후 11:56
 */
@Component
class MembershipServiceAdapter(
    private val globalHttpClient: GlobalHttpClient,
    @Value("\${service.membership.url}") private val membershipServiceUrl: String,
    private val objectMapper: ObjectMapper
): GetMembershipPort {
    override fun getMembership(membershipId: String): MembershipStatus {
        val url = "$membershipServiceUrl/membership/find/$membershipId"
        val response = globalHttpClient.sendGetRequest(url).body()
        val converted = objectMapper.readValue<Membership>(response, Membership::class.java)
        return MembershipStatus(converted.membershipId!!, converted.isValid == true)
    }
}