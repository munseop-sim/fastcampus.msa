package ms2709.payservice.banking.adapter.axon.aggregate;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import ms2709.global.axon.command.RequestFirmbankingCommand;
import ms2709.global.axon.command.RollbackFirmbankingRequestCommand;
import ms2709.global.axon.event.RequestFirmbankingFinishedEvent;
import ms2709.global.axon.event.RollbackFirmbankingFinishedEvent;
import ms2709.payservice.banking.adapter.axon.command.CreateRequestFirmbankingCommand;
import ms2709.payservice.banking.adapter.axon.command.UpdateRequestFirmbankingCommand;
import ms2709.payservice.banking.adapter.axon.event.RequestFirmbankingCreatedEvent;
import ms2709.payservice.banking.adapter.axon.event.UpdateRequestFirmbankingEvent;
import ms2709.payservice.banking.adapter.out.external.bank.ExternalFirmbankingRequest;
import ms2709.payservice.banking.adapter.out.external.bank.FirmbankingResult;
import ms2709.payservice.banking.application.port.out.RequestExternalFirmbankingPort;
import ms2709.payservice.banking.application.port.out.RequestFirmbankingPort;
import ms2709.payservice.banking.domain.FirmbankingRequest;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;


import jakarta.validation.constraints.NotNull;


import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 *  RequestFirmbankingAggregate
 * @since 2024-05-25 오후 3:05
 */
@Aggregate()
@Data
@Slf4j
public class RequestFirmbankingAggregate {
    @AggregateIdentifier
    private String id;

    private String fromBankName;

    private String fromBankAccountNumber;

    private String toBankName;

    private String toBankAccountNumber;

    private int moneyAmount;

    private int firmbankingStatus;

    @CommandHandler
    public RequestFirmbankingAggregate(@NotNull CreateRequestFirmbankingCommand command) {
        log.info("CreateRequestFirmbankingCommand Handler");

        // store event
        apply(new RequestFirmbankingCreatedEvent(command.getFromBankName(), command.getFromBankAccountNumber(), command.getToBankName(), command.getToBankAccountNumber(), command.getMoneyAmount()));
    }

    @CommandHandler
    public String handle(@NotNull UpdateRequestFirmbankingCommand command) {
        log.info("UpdateRequestFirmbankingCommand Handler");
        id = command.getAggregateIdentifier();

        // store event
        apply(new UpdateRequestFirmbankingEvent(command.getFirmbankingStatus()));
        return id;
    }

    @CommandHandler
    public RequestFirmbankingAggregate(RequestFirmbankingCommand command, RequestFirmbankingPort firmbankingPort, RequestExternalFirmbankingPort externalFirmbankingPort){
        System.out.println("RequestFirmbankingCommand Handler");
        id = command.getAggregateIdentifier();

        //펌뱅킹 수행
        firmbankingPort.createFirmbankingRequest(
            new FirmbankingRequest.FromBankName(command.getToBankName()),
            new FirmbankingRequest.FromBankAccountNumber(command.getToBankAccountNumber()),
            new FirmbankingRequest.ToBankName("fastcampus-bank"),
            new FirmbankingRequest.ToBankAccountNumber("123-333-9999"),
            new FirmbankingRequest.MoneyAmount(command.getMoneyAmount()),
            new FirmbankingRequest.FirmBankingStatus(0),
            new FirmbankingRequest.FirmbankingAggregateIdentifier(id));

        // firmbanking!
        FirmbankingResult firmbankingResult = externalFirmbankingPort.requestExternalFirmbanking(new ExternalFirmbankingRequest(
            command.getFromBankName(),
            command.getFromBankAccountNumber(),
            command.getToBankName(),
            command.getToBankAccountNumber(),
            command.getMoneyAmount()
        ));

        int resultCode = firmbankingResult.getResultCode();

        // 0. 성공, 1. 실패
        apply(new RequestFirmbankingFinishedEvent(
            command.getRequestFirmbankingId(),
            command.getRechargeRequestId(),
            command.getMembershipId(),
            command.getToBankName(),
            command.getToBankAccountNumber(),
            command.getMoneyAmount(),
            resultCode,
            id
        ));
    }


    @EventSourcingHandler
    public void on(RequestFirmbankingCreatedEvent event) {
        log.info("RequestFirmbankingCreatedEvent Sourcing Handler");
        id = UUID.randomUUID().toString();
        fromBankName = event.getFromBankName();
        fromBankAccountNumber = event.getFromBankAccountNumber();
        toBankName = event.getToBankName();
        toBankAccountNumber = event.getToBankAccountNumber();
    }

    @EventSourcingHandler
    public void on(UpdateRequestFirmbankingEvent event) {
        log.info("UpdateRequestFirmbankingEvent Sourcing Handler");
        firmbankingStatus = event.getFirmbankingStatus();
    }

    public RequestFirmbankingAggregate() {
    }


    @CommandHandler
    public RequestFirmbankingAggregate(@NotNull RollbackFirmbankingRequestCommand command, RequestFirmbankingPort firmbankingPort, RequestExternalFirmbankingPort externalFirmbankingPort) {
        System.out.println("RollbackFirmbankingRequestCommand Handler");
        id = UUID.randomUUID().toString();

        firmbankingPort.createFirmbankingRequest(
                new FirmbankingRequest.FromBankName("fastcampus-bank"),
                new FirmbankingRequest.FromBankAccountNumber("123-333-9999"),
                new FirmbankingRequest.ToBankName(command.getBankName()),
                new FirmbankingRequest.ToBankAccountNumber(command.getBankAccountNumber()),
                new FirmbankingRequest.MoneyAmount(command.getMoneyAmount()),
                new FirmbankingRequest.FirmBankingStatus(0),
                new FirmbankingRequest.FirmbankingAggregateIdentifier(id));

        // firmbanking!
        externalFirmbankingPort.requestExternalFirmbanking(
                new ExternalFirmbankingRequest(
                        "fastcampus",
                        "123-333-9999",
                        command.getBankName(),
                        command.getBankAccountNumber(),
                        command.getMoneyAmount()
                ));

        apply(new RollbackFirmbankingFinishedEvent(
                command.getRollbackFirmbankingId(),
                command.getMembershipId(),
                id)
        );
    }
}