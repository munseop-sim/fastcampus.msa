package ms2709.payservice.money.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms2709.global.BankTypes;
import ms2709.global.UseCase;
import ms2709.global.kafka.RechargingMoneyTask;
import ms2709.global.kafka.SubTask;
import ms2709.payservice.money.CountDownLatchManager;
import ms2709.payservice.money.adapter.out.persistence.MoneyChangingRequestMapper;
import ms2709.payservice.money.adapter.out.persistence.entity.MemberMoneyJpaEntity;
import ms2709.payservice.money.application.port.in.IncreaseMoneyRequestCommand;
import ms2709.payservice.money.application.port.in.IncreaseMoneyRequestUseCase;
import ms2709.payservice.money.application.port.out.ExternalBankingPort;
import ms2709.payservice.money.application.port.out.IncreaseMoneyPort;
import ms2709.payservice.money.application.port.out.SendRechargingMoneyTaskPort;
import ms2709.payservice.money.domain.MemberMoney;
import ms2709.payservice.money.domain.MoneyChangingRequest;
import ms2709.payservice.money.domain.enums.MoneyChangingRequestStatusTypes;
import ms2709.payservice.money.domain.enums.MoneyChangingTypes;

import java.util.ArrayList;
import java.util.List;
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
@Slf4j
public class IncreaseMoneyRequestService implements IncreaseMoneyRequestUseCase {
    private final IncreaseMoneyPort increaseMoneyPort;
    private final MoneyChangingRequestMapper mapper;
    private final ExternalBankingPort externalBankingPort;
    private final SendRechargingMoneyTaskPort sendRechargingMoneyTaskPort;
    private final CountDownLatchManager countDownLatchManager;

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

    @Override
    public MoneyChangingRequest increaseMoneyRequestAsync(IncreaseMoneyRequestCommand command) {
        // 비동기로 처리하는 경우
        log.info("increaseMoneyRequestAsync run");
       countDownLatchManager.addCountDownLatch(RechargingMoneyTask.TASK_NAME);

        // SubTask - 각 서비스에 특정 membershipId로 Validation을 하기 위한 Task
        // 1. SubTask, Task 정의

        SubTask validateMembershipIdTask = SubTask.builder()
                .subTaskName("validateMembershipId")
                .membershipID(command.getTargetMembershipId())
                .taskType(SubTask.TaskType.MEMBERSHIP)
                .status(SubTask.Status.READY)
                .build();

        SubTask validateBankingTask = SubTask.builder()
                .subTaskName("validateBanking")
                .membershipID(command.getTargetMembershipId())
                .taskType(SubTask.TaskType.BANKING)
                .status(SubTask.Status.READY)
                .build();
        List<SubTask> subTaskList = new ArrayList<>();
        subTaskList.add(validateMembershipIdTask);
        subTaskList.add(validateBankingTask);

        RechargingMoneyTask task = RechargingMoneyTask.builder()
                .taskID(UUID.randomUUID().toString())
                .subTaskList(subTaskList)
                .moneyAmount(command.getAmount())
                .membershipID(command.getTargetMembershipId())
                .toBankName(BankTypes.MS2709) // 법인 계좌 은행 이름
                .build();

        // 2. Kafka Cluster Produce
        sendRechargingMoneyTaskPort.sendRechargingMoneyTaskPort(task);
        // 3. Wait
        // block, wait....
        try {
            // Task 완료 이벤트 올 때까지 기다린다.
            countDownLatchManager.getCountDownLatch(RechargingMoneyTask.TASK_NAME).await();
            String result = countDownLatchManager.getDataForKey(task.getTaskID());
            if (result.equals("success")) {
                // 제대로 수행됨,, 머니 증액.
                log.info("success for async Money Recharging!!");
                return getMoneyChangingRequest(command);
            } else {
                return null;
            }
        } catch (InterruptedException e) {
            // 문제 발생 시 핸들링.
            throw new RuntimeException(e);
        }

        // 3-1. task-consumer
        // 등록된 sub-task, status 모두 ok -> task결과를 produce
        // 4. task result consume
        // 5. consume ok, Logic 실행

    }

    private MoneyChangingRequest getMoneyChangingRequest(IncreaseMoneyRequestCommand command) {
        MemberMoneyJpaEntity memberMoneyJpaEntity = increaseMoneyPort.increaseMoney(
                new MemberMoney.MembershipId(command.getTargetMembershipId())
                ,command.getAmount());

        if(memberMoneyJpaEntity != null) {
            return mapper.mapToDomainEntity(increaseMoneyPort.createMoneyChangingRequest(
                            new MoneyChangingRequest.TargetMembershipId(command.getTargetMembershipId()),
                            MoneyChangingTypes.INCREASE,
                            new MoneyChangingRequest.ChangingMoneyAmount(command.getAmount()),
                            MoneyChangingRequestStatusTypes.SUCCESS,
                            new MoneyChangingRequest.Uuid(UUID.randomUUID().toString())
                    )
            );
        }

        return null;
    }
}
