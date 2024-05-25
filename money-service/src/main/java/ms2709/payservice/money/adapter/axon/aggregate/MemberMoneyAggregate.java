package ms2709.payservice.money.adapter.axon.aggregate;


import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import ms2709.payservice.money.adapter.axon.command.CreateMoneyCommand;
import ms2709.payservice.money.adapter.axon.command.IncreaseMoneyRequestEventCommand;
import ms2709.payservice.money.adapter.axon.command.RechargingMoneyRequestCreateCommand;
import ms2709.payservice.money.adapter.axon.event.IncreaseMoneyEvent;
import ms2709.payservice.money.adapter.axon.event.MemberMoneyCreateEvent;
import ms2709.payservice.money.adapter.axon.event.RechargingRequestCreatedEvent;
import ms2709.payservice.money.application.port.out.GetRegisteredBankAccountPort;
import ms2709.payservice.money.application.port.out.RegisteredBankAccountAggregateIdentifier;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;
/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class MemberMoneyAggregate
 * @since 2024-05-25 오후 1:39
 */

//@Table(name = "member_money")
@Aggregate()
@Data
@Slf4j
public class MemberMoneyAggregate {
    @AggregateIdentifier
    private String id;

    private Long membershipId;

    private int balance;

    @CommandHandler
    public MemberMoneyAggregate(@NotNull CreateMoneyCommand command) {
        log.info("CreateMoneyCommand Handler");
        // store event
        apply(new MemberMoneyCreateEvent(command.getMembershipId()));
    }

    @CommandHandler
    public String handleIncreaseMoney(@NotNull IncreaseMoneyRequestEventCommand command) {
        log.info("IncreaseMoneyRequestEventCommand Handler");
        id = command.getAggregateIdentifier();

        // store event
        apply(new IncreaseMoneyEvent(id, command.getTargetMembershipId(), command.getAmount()));
        return id;
    }

    @CommandHandler
    public void handle(RechargingMoneyRequestCreateCommand command, GetRegisteredBankAccountPort getRegisteredBankAccountPort) {
        log.info("RechargingMoneyRequestCreateCommand Handler");
        id = command.getAggregateIdentifier();

        log.info("RechargingMoneyRequestCreateCommand Handler command aggregate identifier : " + command.getAggregateIdentifier());
        RegisteredBankAccountAggregateIdentifier bankAccountAggregate = getRegisteredBankAccountPort.getRegisteredBankAccount(command.getMembershipId());
        log.info("RechargingMoneyRequestCreateCommand Handler command bankAccountAggregate : " + bankAccountAggregate);
        apply(new RechargingRequestCreatedEvent(
                command.getRechargingRequestId()
                , command.getMembershipId()
                , command.getAmount()
                , bankAccountAggregate.getAggregateIdentifier()
                , bankAccountAggregate.getBankName()
                , bankAccountAggregate.getBankAccountNumber()
        ));
    }

    @EventSourcingHandler
    public void on(MemberMoneyCreateEvent event) {
        log.info("MemberMoneyCreateEvent Sourcing Handler");
        id = UUID.randomUUID().toString();
        membershipId = Long.parseLong(event.getMembershipId());
        balance = 0;
    }

    @EventSourcingHandler
    public void on(IncreaseMoneyEvent event) {
        log.info("IncreaseMoneyEvent Sourcing Handler");
        id = event.getAggregateIdentifier();
        membershipId = Long.parseLong(event.getTargetMembershipId());
        balance = event.getAmount();
    }

    public MemberMoneyAggregate() {
        // Required by Axon to construct an empty instance to initiate Event Sourcing.
    }

    @Override
    public String toString() {
        return "MemberMoneyAggregate{" +
                "id='" + id + '\'' +
                ", membershipId=" + membershipId +
                ", balance=" + balance +
                '}';
    }
}