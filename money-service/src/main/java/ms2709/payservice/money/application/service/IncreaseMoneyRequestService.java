package ms2709.payservice.money.application.service;

import lombok.RequiredArgsConstructor;
import ms2709.global.UseCase;
import ms2709.payservice.money.adapter.out.persistence.MoneyChangingRequestMapper;
import ms2709.payservice.money.adapter.out.persistence.entity.MemberMoneyJpaEntity;
import ms2709.payservice.money.application.port.in.IncreaseMoneyRequestCommand;
import ms2709.payservice.money.application.port.in.IncreaseMoneyRequestUseCase;
import ms2709.payservice.money.application.port.out.ExternalBankingPort;
import ms2709.payservice.money.application.port.out.IncreaseMoneyPort;
import ms2709.payservice.money.domain.MemberMoney;
import ms2709.payservice.money.domain.MoneyChangingRequest;
import ms2709.payservice.money.domain.enums.MoneyChangingRequestStatusTypes;
import ms2709.payservice.money.domain.enums.MoneyChangingTypes;

import java.util.UUID;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class IncreaseMoneyRequestService
 * @since 2024-05-16 오전 8:33
 */
@UseCase
@RequiredArgsConstructor
public class IncreaseMoneyRequestService implements IncreaseMoneyRequestUseCase {
    private final IncreaseMoneyPort increaseMoneyPort;
    private final MoneyChangingRequestMapper mapper;
    private final ExternalBankingPort externalBankingPort;

    @Override
    public MoneyChangingRequest increaseMoneyRequest(IncreaseMoneyRequestCommand command) {
        // 머니의 충전.증액이라는 과정
        // 1. 고객 정보가 정상인지 확인 (멤버)

        // 2. 고객의 연동된 계좌가 있는지, 고객의 연동된 계좌의 잔액이 충분한지도 확인 (뱅킹)

        // 3. 법인 계좌 상태도 정상인지 확인 (뱅킹)

        // 4. 증액을 위한 "기록". 요청 상태로 MoneyChangingRequest 를 생성한다. (MoneyChangingRequest)
        var moneyChangingRequest = increaseMoneyPort.createMoneyChangingRequest(
                new MoneyChangingRequest.TargetMembershipId(command.getTargetMembershipId()),
                MoneyChangingTypes.INCREASE,
                new MoneyChangingRequest.ChangingMoneyAmount(command.getAmount()),
                MoneyChangingRequestStatusTypes.REQUESTED,
                new MoneyChangingRequest.Uuid(UUID.randomUUID().toString())
        );

        // 5. 펌뱅킹을 수행하고 (고객의 연동된 계좌 -> 패캠페이 법인 계좌) (뱅킹)
        var bankingIncreaseResult = externalBankingPort.increaseAccountMoney(command.getTargetMembershipId(), command.getAmount());

        if(bankingIncreaseResult){
            // 6-1. 결과가 정상적이라면. 성공으로 MoneyChangingRequest 상태값을 변동 후에 리턴
            // 성공 시에 멤버의 MemberMoney 값 증액이 필요해요
            MemberMoneyJpaEntity memberMoneyJpaEntity = increaseMoneyPort.increaseMoney(
                    new MemberMoney.MembershipId(moneyChangingRequest.getTargetMembershipId()),
                    moneyChangingRequest.getMoneyAmount());
            return mapper.mapToDomainEntity(
                increaseMoneyPort.modifyStatus(
                        new MoneyChangingRequest.MoneyChangingRequestId(moneyChangingRequest.getMoneyChangingRequestId()+""),
                        MoneyChangingRequestStatusTypes.SUCCESS
                )
            );
        }else{
            // 6-2. 결과가 실패라면, 실패라고 MoneyChangingRequest 상태값을 변동 후에 리턴
            return mapper.mapToDomainEntity(
                increaseMoneyPort.modifyStatus(
                        new MoneyChangingRequest.MoneyChangingRequestId(moneyChangingRequest.getMoneyChangingRequestId()+""),
                        MoneyChangingRequestStatusTypes.FAILED
                )
            );
        }


    }
}
