package ms2709.payservice.banking.application.port.out;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class GetMembershipPort
 * @since 2024-05-20 오후 9:56
 */
public interface GetMembershipPort {
    MembershipStatus getMembershipStatus(String membershipId);
}
