package ms2709.payservice.money.application.port.out;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 *  GetMembershipPort
 * @since 2024-05-25 오후 2:21
 */
public interface GetMembershipPort {
    MembershipStatus getMembership(String membershipId);
}
