package ms2709.payservice.money.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class MemberMoney
 * @since 2024-05-16 오전 8:43
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberMoney {
    @Getter
    private final String memberMoneyId;

    @Getter
    private final String membershipId;

    //잔액
    @Getter
    private final Integer balance;

    public static MemberMoney create(
            MemberMoneyId memberMoneyId,
            MembershipId membershipId,
            Balance balance
    ) {
        return new MemberMoney(
                memberMoneyId.value,
                membershipId.value,
                balance.value
        );
    }
    public record MemberMoneyId(String value){}
    public record MembershipId(String value){}
    public record Balance(Integer value){}
    public record MoneyAggregateIdentifier(String value){}
}
