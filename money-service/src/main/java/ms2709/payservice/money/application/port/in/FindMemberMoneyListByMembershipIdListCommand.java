package ms2709.payservice.money.application.port.in;

import java.util.List;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * FindMemberMoneyListByMembershipIdListCommand
 * @since 2024-06-11 오전 10:46
 */
public record FindMemberMoneyListByMembershipIdListCommand (List<String> membershipIdList) {
}
