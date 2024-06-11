package ms2709.payservice.money.application.port.in;

import ms2709.payservice.money.domain.MemberMoney;

import java.util.List;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 *  FindMoneyUseCase
 * @since 2024-06-11 오전 10:47
 */
public interface FindMoneyUseCase {
    List<MemberMoney> findMemberMoneyListByMembershipList(FindMemberMoneyListByMembershipIdListCommand command);
}
