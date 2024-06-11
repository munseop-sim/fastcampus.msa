package ms2709.payservice.money.application.port.out;

import ms2709.payservice.money.adapter.out.persistence.entity.MemberMoneyJpaEntity;
import ms2709.payservice.money.adapter.out.persistence.entity.MoneyChangingRequestJpaEntity;
import ms2709.payservice.money.domain.MemberMoney;
import ms2709.payservice.money.domain.MoneyChangingRequest;
import ms2709.payservice.money.domain.enums.MoneyChangingRequestStatusTypes;
import ms2709.payservice.money.domain.enums.MoneyChangingTypes;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 *  IncreaseMoneyPort
 * @since 2024-05-16 오전 8:34
 */
public interface IncreaseMoneyPort {
    MoneyChangingRequestJpaEntity createMoneyChangingRequest(
            MoneyChangingRequest.TargetMembershipId targetMembershipId,
            MoneyChangingTypes moneyChangingType,
            MoneyChangingRequest.ChangingMoneyAmount changingMoneyAmount,
            MoneyChangingRequestStatusTypes moneyChangingStatus,
            MoneyChangingRequest.Uuid uuid
    );

    MemberMoneyJpaEntity increaseMoney(
            MemberMoney.MembershipId memberId,
            int increaseMoneyAmount
    );

    MoneyChangingRequestJpaEntity modifyStatus(MoneyChangingRequest.MoneyChangingRequestId moneyChangingRequestId, MoneyChangingRequestStatusTypes status);
}
