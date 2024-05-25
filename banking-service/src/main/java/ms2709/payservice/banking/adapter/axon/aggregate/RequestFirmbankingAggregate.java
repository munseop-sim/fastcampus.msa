package ms2709.payservice.banking.adapter.axon.aggregate;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import ms2709.payservice.banking.adapter.axon.command.CreateRequestFirmbankingCommand;
import ms2709.payservice.banking.adapter.axon.command.UpdateRequestFirmbankingCommand;
import ms2709.payservice.banking.adapter.axon.event.RequestFirmbankingCreatedEvent;
import ms2709.payservice.banking.adapter.axon.event.UpdateRequestFirmbankingEvent;
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
 * @class RequestFirmbankingAggregate
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
}