package ms2709.payservice.banking.adapter.axon.aggregate;


import lombok.extern.slf4j.Slf4j;
import ms2709.global.axon.command.CheckRegisteredBankAccountCommand;
import ms2709.global.axon.event.CheckedRegisteredBankAccountEvent;
import ms2709.payservice.banking.adapter.axon.command.CreateRegisteredBankAccountCommand;
import ms2709.payservice.banking.adapter.axon.event.CreateRegisteredBankAccountEvent;
import ms2709.payservice.banking.adapter.out.external.bank.BankAccount;
import ms2709.payservice.banking.adapter.out.external.bank.GetBankAccountRequest;
import ms2709.payservice.banking.application.port.out.RequestBankAccountInfoPort;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Slf4j
@Aggregate()
public class RegisteredBankAccountAggregate {
    @AggregateIdentifier
    private String id;

    private String membershipId;

    private String bankName;

    private String bankAccountNumber;

    @CommandHandler
    public RegisteredBankAccountAggregate(@NotNull CreateRegisteredBankAccountCommand command) {
        log.info("CreateRegisteredBankAccountCommand Handler");

        // store event
        apply(new CreateRegisteredBankAccountEvent(command.getMembershipId(), command.getBankName(), command.getBankAccountNumber()));
    }

    @CommandHandler
    public void handle(@NotNull CheckRegisteredBankAccountCommand command, RequestBankAccountInfoPort bankAccountInfoPort) {
        log.info("CheckRegisteredBankAccountCommand Handler");

        id = command.getAggregateIdentifier();
        //command를 통해 이 어그리거트(RegisteredBankAccount)가 정상인지 확인 필요
        BankAccount bankAccount = bankAccountInfoPort.getBankAccountInfo(new GetBankAccountRequest(command.getBankName(), command.getBankAccountNumber()));
        String firmbankingUUID = UUID.randomUUID().toString();

        apply(new CheckedRegisteredBankAccountEvent(
                        command.getRechargingRequestId()
                        , command.getCheckRegisteredBankAccountId()
                        , command.getMembershipId()
                        , bankAccount.isValid()
                        , command.getAmount()
                        , firmbankingUUID
                        , bankAccount.getBankName()
                        , bankAccount.getBankAccountNumber()
                )
        );
    }

    @EventSourcingHandler
    public void on(CreateRegisteredBankAccountEvent event) {
        log.info("CreateRegisteredBankAccountEvent Sourcing Handler");
        id = UUID.randomUUID().toString();
        membershipId = event.getMembershipId();
        bankName = event.getBankName();
        bankAccountNumber = event.getBankAccountNumber();
    }

    public RegisteredBankAccountAggregate() {
        // System.out.println("RegisteredBankAccountAggregate Constructor");
    }
}