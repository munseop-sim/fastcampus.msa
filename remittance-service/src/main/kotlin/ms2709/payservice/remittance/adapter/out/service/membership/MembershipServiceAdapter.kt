package ms2709.payservice.remittance.adapter.out.service.membership

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import ms2709.global.ExternalSystemAdapter
import ms2709.global.GlobalHttpClient
import ms2709.payservice.remittance.application.port.out.membership.MembershipPort
import ms2709.payservice.remittance.application.port.out.membership.MembershipStatus
import org.springframework.beans.factory.annotation.Value

/**
 *
 * 클래스 설명
 *
 *  MembershipServiceAdapter
 * @author 심문섭
 * @version 1.0
 * @since 2024-05-25 오전 8:15
 */
@ExternalSystemAdapter
class MembershipServiceAdapter (
    private val globalHttpClient: GlobalHttpClient,
    private val objectMapper: ObjectMapper
): MembershipPort {

    @Value("\${service.membership.url}")
    lateinit var membershipServiceEndpoint: String

    override fun getMembershipStatus(membershipId: String): MembershipStatus {
        val buildUrl = java.lang.String.join("/", this.membershipServiceEndpoint, "membership", "find", membershipId)
        return kotlin.runCatching {
            val member = globalHttpClient.sendGetRequest(buildUrl).body().run {
                objectMapper.readValue(this, MembershipStatus::class.java)
            }

            MembershipStatus(member.membershipId, (member.isValid))
        }.onFailure {
            throw RuntimeException("Failed to get membership status", it)
        }.getOrThrow()
    }
}