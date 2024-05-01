package ms2709.member.adapter.`in`.web

import com.fasterxml.jackson.databind.ObjectMapper
import ms2709.member.domain.Membership
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.mock.http.server.reactive.MockServerHttpRequest.post
import org.springframework.test.web.client.match.MockRestRequestMatchers.content
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/**
 * @class RegisterMembershipControllerTest
 * @author 심문섭
 * @version 1.0
 * @since 2024-05-01 오전 10:28
 * @modified
 */

@SpringBootTest
@AutoConfigureMockMvc
class RegisterMembershipControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val objectMapper: ObjectMapper
){

    @DisplayName("api를 호출하여 멤버를 등록할 수 있다.")
    @Test
    fun testRegisterMember(){
        val request = RegisterMembershipRequest(
            name = "tester",
            email = "email",
            address = "address",
            isCorp = false)

        val expect = Membership.create(
            membershipId =  Membership.MembershipId(1.toString()),
            name= Membership.MemberName(request.name),
            email = Membership.MemberEmail(request.email),
            address = Membership.MemberAddress(request.address),
            isValid = Membership.MemberIsValid(true),
            isCorp = Membership.MemberIsCorp(request.isCorp)
        )

        mockMvc.perform(
            MockMvcRequestBuilders.post("/membership/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(expect))
        )
    }
}