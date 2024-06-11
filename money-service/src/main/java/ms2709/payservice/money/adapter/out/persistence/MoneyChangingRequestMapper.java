package ms2709.payservice.money.adapter.out.persistence;

import ms2709.payservice.money.adapter.out.persistence.entity.MoneyChangingRequestJpaEntity;
import ms2709.payservice.money.domain.MoneyChangingRequest;
import ms2709.payservice.money.domain.enums.MoneyChangingRequestStatusTypes;
import ms2709.payservice.money.domain.enums.MoneyChangingTypes;
import org.springframework.stereotype.Component;

/**
 *  jpa entity를 domain entity로 변환하는 mapper
 *
 * @author 심문섭
 * @version 1.0
 *  MoneyChangingRequestMapper
 * @since 2024-05-16 오전 8:34
 */
@Component
public class MoneyChangingRequestMapper {
    public MoneyChangingRequest mapToDomainEntity(MoneyChangingRequestJpaEntity moneyChangingRequestJpaEntity) {
        return MoneyChangingRequest.create(
                new MoneyChangingRequest.MoneyChangingRequestId(moneyChangingRequestJpaEntity.getMoneyChangingRequestId()+""),
                new MoneyChangingRequest.TargetMembershipId(moneyChangingRequestJpaEntity.getTargetMembershipId()),
                new MoneyChangingRequest.ChangingType(
                    MoneyChangingTypes.from(
                        moneyChangingRequestJpaEntity.getMoneyChangingType()
                    )
                ),

                new MoneyChangingRequest.ChangingMoneyStatus(
                    MoneyChangingRequestStatusTypes.from(
                        moneyChangingRequestJpaEntity.getChangingMoneyStatus()
                    )
                ),
                new MoneyChangingRequest.ChangingMoneyAmount(moneyChangingRequestJpaEntity.getMoneyAmount()),
                new MoneyChangingRequest.Uuid(moneyChangingRequestJpaEntity.getUuid())
        );
    }
}