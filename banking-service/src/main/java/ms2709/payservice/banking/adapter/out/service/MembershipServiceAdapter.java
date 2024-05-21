package ms2709.payservice.banking.adapter.out.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import ms2709.global.GlobalHttpClient;
import ms2709.payservice.banking.application.port.out.GetMembershipPort;
import ms2709.payservice.banking.application.port.out.MembershipStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class MembershipServiceAdapter
 * @since 2024-05-20 오후 10:00
 */
@Component
public class MembershipServiceAdapter implements GetMembershipPort {

    private final GlobalHttpClient globalHttpClient;

    private final String membershipServiceUrl;

    private final ObjectMapper objectMapper;
    public MembershipServiceAdapter(GlobalHttpClient globalHttpClient,
                                    @Value("${membership.service.url}") String membershipServiceUrl,
    ObjectMapper objectMapper) {
        this.globalHttpClient = globalHttpClient;
        this.membershipServiceUrl = membershipServiceUrl;
        this.objectMapper = objectMapper;
    }



    @Override
    public MembershipStatus getMembershipStatus(String membershipId) {
        String url = String.join("/", membershipServiceUrl,"membership","find", membershipId);
        try {
            String response = globalHttpClient.sendGetRequest(url).body();
            Membership membership = objectMapper.readValue(response, Membership.class);
            return new MembershipStatus(membership.getMembershipId(), membership.isValid());
        } catch (Exception e) {
            return new MembershipStatus(membershipId, false);
        }
    }
}
