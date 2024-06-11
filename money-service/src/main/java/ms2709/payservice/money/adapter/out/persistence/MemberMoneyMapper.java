package ms2709.payservice.money.adapter.out.persistence;

import ms2709.payservice.money.adapter.out.persistence.entity.MemberMoneyJpaEntity;
import ms2709.payservice.money.domain.MemberMoney;
import org.springframework.stereotype.Component;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class MemberMoneyMapper
 * @since 2024-06-11 오전 11:01
 */
@Component
public class MemberMoneyMapper {
    public MemberMoney mapToEntity(MemberMoneyJpaEntity memberMoneyJpaEntity) {
        return MemberMoney.create(
                new MemberMoney.MemberMoneyId(memberMoneyJpaEntity.getMemberMoneyId().toString()),
                new MemberMoney.MembershipId(memberMoneyJpaEntity.getMembershipId().toString()),
                new MemberMoney.Balance(memberMoneyJpaEntity.getBalance())
        );
    }
}
