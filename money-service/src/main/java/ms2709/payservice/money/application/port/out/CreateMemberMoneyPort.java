package ms2709.payservice.money.application.port.out;

import ms2709.payservice.money.domain.MemberMoney;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class CreateMemberMoneyPort
 * @since 2024-05-25 오후 2:18
 */
public interface CreateMemberMoneyPort {
    void createMemberMoney(
            MemberMoney.MembershipId memberId,
            MemberMoney.MoneyAggregateIdentifier aggregateIdentifier
    );
}