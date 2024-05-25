package ms2709.payservice.banking.application.service;

import ms2709.payservice.banking.adapter.axon.command.CreateRequestFirmbankingCommand;
import ms2709.payservice.banking.adapter.axon.command.UpdateRequestFirmbankingCommand;
import ms2709.payservice.banking.adapter.out.external.bank.ExternalFirmbankingRequest;
import ms2709.payservice.banking.adapter.out.external.bank.FirmbankingResult;
import ms2709.payservice.banking.adapter.out.persistence.FirmbankingRequestMapper;
import ms2709.payservice.banking.adapter.out.persistence.entity.FirmbankingRequestJpaEntity;
import ms2709.payservice.banking.application.port.in.FindRequestFirmbankingCommand;
import ms2709.payservice.banking.application.port.in.FindRequestFirmbankingUseCase;
import ms2709.payservice.banking.application.port.in.RequestFirmbankingCommand;
import ms2709.payservice.banking.application.port.in.RequestFirmbankingUseCase;
import ms2709.payservice.banking.application.port.out.FindFirmbankingRequestPort;
import ms2709.payservice.banking.application.port.out.RequestExternalFirmbankingPort;
import ms2709.payservice.banking.application.port.out.RequestFirmbankingPort;
import ms2709.payservice.banking.domain.FirmbankingRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import ms2709.global.UseCase;
import org.axonframework.commandhandling.gateway.CommandGateway;

import java.util.UUID;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class RequestFirmbankingService
 * @since 2024-05-15 오후 12:00
 */

@UseCase
@RequiredArgsConstructor
@Transactional
public class RequestFirmbankingService implements RequestFirmbankingUseCase, FindRequestFirmbankingUseCase {

    private final FirmbankingRequestMapper mapper;
    private final RequestFirmbankingPort requestFirmbankingPort;
    private final FindFirmbankingRequestPort findFirmbankingRequestPort;
    private final RequestExternalFirmbankingPort requestExternalFirmbankingPort;
    private final CommandGateway commandGateway;
    @Override
    public FirmbankingRequest requestFirmbanking(RequestFirmbankingCommand command) {
        //business logic
        // a->b 계좌로의 이체

        //"요청" 상태로 계좌이체 요청에 대한 정보를 저장
        var entity = requestFirmbankingPort.createFirmbankingRequest(
            new FirmbankingRequest.FromBankName(command.getFromBankName()),
            new FirmbankingRequest.FromBankAccountNumber(command.getFromBankAccountNumber()),
            new FirmbankingRequest.ToBankName(command.getToBankName()),
            new FirmbankingRequest.ToBankAccountNumber(command.getToBankAccountNumber()),
            new FirmbankingRequest.MoneyAmount(command.getMoneyAmount()),
            new FirmbankingRequest.FirmBankingStatus(0),
            new FirmbankingRequest.FirmbankingAggregateIdentifier("")
        );
        //transactional UUID
        var randomUUID = UUID.randomUUID();
        entity.setUuid(randomUUID.toString());

        //외부 은행 서비스를 호출하여 계좌이체 요청
        FirmbankingResult result = requestExternalFirmbankingPort.requestExternalFirmbanking(
                new ExternalFirmbankingRequest(
                        command.getFromBankName(),
                        command.getFromBankAccountNumber(),
                        command.getToBankName(),
                        command.getToBankAccountNumber()
                )
        );

        if(result.getResultCode() == 0){
            //성공적으로 외부 은행 서비스를 호출한 경우
            //외부 은행 서비스의 결과를 "성공" 상태로 변경
            entity.setFirmBankingStatus(1);
        }else{
            //외부 은행 서비스 호출에 실패한 경우
            //외부 은행 서비스의 결과를 "실패" 상태로 변경
            entity.setFirmBankingStatus(2);
        }

        var saved = requestFirmbankingPort.modifyFirmbankingRequest(entity);
        return mapper.mapToDomainEntity(saved, randomUUID, saved.getAggregateIdentifier());
    }

    @Override
    public void requestFirmbankingByEvent(RequestFirmbankingCommand command) {
        commandGateway.send(new CreateRequestFirmbankingCommand(
                command.getFromBankName(),
                command.getFromBankAccountNumber(),
                command.getToBankName(),
                command.getToBankAccountNumber(),
                command.getMoneyAmount()
        )).whenComplete((result, throwable) -> {
            if (throwable == null) {
                System.out.println("RequestFirmbankingCommand Aggregate ID:" + result.toString());

                FirmbankingRequestJpaEntity requestedEntity = requestFirmbankingPort.createFirmbankingRequest(
                        new FirmbankingRequest.FromBankName(command.getFromBankName()),
                        new FirmbankingRequest.FromBankAccountNumber(command.getFromBankAccountNumber()),
                        new FirmbankingRequest.ToBankName(command.getToBankName()),
                        new FirmbankingRequest.ToBankAccountNumber(command.getToBankAccountNumber()),
                        new FirmbankingRequest.MoneyAmount(command.getMoneyAmount()),
                        new FirmbankingRequest.FirmBankingStatus(0),
                        new FirmbankingRequest.FirmbankingAggregateIdentifier(result.toString())
                );

                // 2. 외부 은행에 펌뱅킹 요청
                FirmbankingResult firmbankingResult = requestExternalFirmbankingPort.requestExternalFirmbanking(new ExternalFirmbankingRequest(
                        command.getFromBankName(),
                        command.getFromBankAccountNumber(),
                        command.getToBankName(),
                        command.getToBankAccountNumber()
                ));

                // Transactional UUID
                UUID randomUUID = UUID.randomUUID();
                requestedEntity.setUuid(randomUUID.toString());

                // 3. 결과에 따라서 1번에서 작성했던 FirmbankingRequest 정보를 Update
                if (firmbankingResult.getResultCode() == 0){
                    // 성공
                    requestedEntity.setFirmBankingStatus(1);
                } else {
                    // 실패
                    requestedEntity.setFirmBankingStatus(2);
                }

            } else {
                throwable.printStackTrace();
                System.out.println("error : " + throwable.getMessage());
            }
        });
    }

    @Override
    public void updateFirmbankingByEvent(UpdateRequestFirmbankingCommand command) {
        commandGateway.send(command)
                .whenComplete((result, throwable) -> {
                    if (throwable == null) {
                        System.out.println("updateFirmbankingByEvent Aggregate ID:" + result.toString());
                        FirmbankingRequestJpaEntity requestedEntity = requestFirmbankingPort.getFirmbankingRequest(
                                new FirmbankingRequest.FirmbankingAggregateIdentifier(result.toString())
                        );

                        // 2. 외부 은행에 펌뱅킹 요청
//                FirmbankingResult firmbankingResult = requestExternalFirmbankingPort.requestExternalFirmbanking(new ExternalFirmbankingRequest(
//                        command.getFromBankName(),
//                        command.getFromBankAccountNumber(),
//                        command.getToBankName(),
//                        command.getToBankAccountNumber()
//                ));
                        requestedEntity.setFirmBankingStatus(command.getFirmbankingStatus());
                        requestFirmbankingPort.modifyFirmbankingRequest(requestedEntity);
                    } else {
                        System.out.println("error : " + throwable.getMessage());
                        throwable.printStackTrace();
                    }
                });
    }

    @Override
    public FirmbankingRequest findRequestFirmbanking(FindRequestFirmbankingCommand command) {
        var entity = findFirmbankingRequestPort.findFirmbankingRequest(
                new FirmbankingRequest.FirmbankingRequestId(command.getFirmbankingRequestId() + "")
        );
        if(entity == null){
            throw new RuntimeException("Not Found");
        }
        return mapper.mapToDomainEntity(entity, UUID.fromString(entity.getUuid()), entity.getAggregateIdentifier());
    }
}
