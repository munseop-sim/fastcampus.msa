package ms2709.payservice.money.adapter.out;

import lombok.RequiredArgsConstructor;
import ms2709.global.PersistenceAdapter;
import ms2709.payservice.money.adapter.out.persistence.entity.MemberMoneyJpaEntity;
import ms2709.payservice.money.adapter.out.persistence.entity.MoneyChangingRequestJpaEntity;
import ms2709.payservice.money.adapter.out.persistence.repository.MemberMoneyJpaEntityRepository;
import ms2709.payservice.money.adapter.out.persistence.repository.MoneyChangingRequestJpEntityRepository;
import ms2709.payservice.money.application.port.out.CreateMemberMoneyPort;
import ms2709.payservice.money.application.port.out.GetMemberMoneyPort;
import ms2709.payservice.money.application.port.out.IncreaseMoneyPort;
import ms2709.payservice.money.domain.MemberMoney;
import ms2709.payservice.money.domain.MoneyChangingRequest;
import ms2709.payservice.money.domain.enums.MoneyChangingRequestStatusTypes;
import ms2709.payservice.money.domain.enums.MoneyChangingTypes;

import java.time.LocalDate;
import java.util.List;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class MoneyChangingRequestPersistenceAdapter
 * @since 2024-05-16 오전 8:40
 */
@RequiredArgsConstructor
@PersistenceAdapter
public class MoneyChangingRequestPersistenceAdapter implements IncreaseMoneyPort, CreateMemberMoneyPort {
    private final MemberMoneyJpaEntityRepository memberMoneyJpaEntityRepository;
    private final MoneyChangingRequestJpEntityRepository moneyChangingRequestJpEntityRepository;

    @Override
    public MoneyChangingRequestJpaEntity createMoneyChangingRequest(MoneyChangingRequest.TargetMembershipId targetMembershipId, MoneyChangingTypes moneyChangingType, MoneyChangingRequest.ChangingMoneyAmount changingMoneyAmount, MoneyChangingRequestStatusTypes moneyChangingStatus, MoneyChangingRequest.Uuid uuid) {
        return moneyChangingRequestJpEntityRepository.save(
                new MoneyChangingRequestJpaEntity(
                        targetMembershipId.value(),
                        moneyChangingType.getChangingTypeIndex(),
                        changingMoneyAmount.value(),
                        LocalDate.now(),
                        moneyChangingStatus.getStatusIndex(),
                        uuid.value()
                )
        );
    }

    @Override
    public MemberMoneyJpaEntity increaseMoney(MemberMoney.MembershipId membershipId, int increaseMoneyAmount) {
        MemberMoneyJpaEntity entity;

        List<MemberMoneyJpaEntity> entityList =  memberMoneyJpaEntityRepository.findByMembershipId(Long.parseLong(membershipId.value()));
        entity = entityList.get(0);

        entity.setBalance(entity.getBalance() + increaseMoneyAmount);
        return  memberMoneyJpaEntityRepository.save(entity);
    }

    @Override
    public MoneyChangingRequestJpaEntity modifyStatus(MoneyChangingRequest.MoneyChangingRequestId moneyChangingRequestId, MoneyChangingRequestStatusTypes status) {
        var targetItem = moneyChangingRequestJpEntityRepository.findById(Long.parseLong(moneyChangingRequestId.value()))
                .orElseThrow(() -> new IllegalArgumentException("해당하는 요청이 없습니다."));

        targetItem.setChangingMoneyStatus(status.getStatusIndex());
        return moneyChangingRequestJpEntityRepository.save(targetItem);
    }

    @Override
    public void createMemberMoney(MemberMoney.MembershipId memberId, MemberMoney.MoneyAggregateIdentifier aggregateIdentifier) {
        MemberMoneyJpaEntity entity = new MemberMoneyJpaEntity(
                Long.parseLong(memberId.value()),
                0, aggregateIdentifier.value()
        );
        memberMoneyJpaEntityRepository.save(entity);
    }


}
