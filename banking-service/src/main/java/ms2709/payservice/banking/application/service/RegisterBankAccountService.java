package ms2709.payservice.banking.application.service;

import ms2709.payservice.banking.adapter.axon.command.CreateRegisteredBankAccountCommand;
import ms2709.payservice.banking.adapter.out.external.bank.BankAccount;
import ms2709.payservice.banking.adapter.out.external.bank.GetBankAccountRequest;
import ms2709.payservice.banking.adapter.out.persistence.RegisteredBankAccountMapper;
import ms2709.payservice.banking.application.port.in.*;
import ms2709.payservice.banking.application.port.out.*;
import ms2709.payservice.banking.domain.RegisteredBankAccount;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import ms2709.global.UseCase;
import org.axonframework.commandhandling.gateway.CommandGateway;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class RegisterBankAccountService
 * @since 2024-05-13 오후 11:21
 */

@UseCase
@RequiredArgsConstructor
@Transactional
public class RegisterBankAccountService implements RegisterBankAccountUseCase, FindBankAccountUseCase, GetRegisteredBankAccountUseCase {

    private final GetMembershipPort getMembershipPort;
    private final RegisterBankAccountPort registerBankAccountPort;
    private final FindBankAccountPort findBankAccountPort;
    private final RequestBankAccountInfoPort requestBankAccountIntoPort;
    private final RegisteredBankAccountMapper registeredBankAccountMapper;
    private final CommandGateway commandGateway;
    private final GetRegisteredBankAccountPort getRegisteredBankAccountPort;
    @Override
    public RegisteredBankAccount registerBankAccount(RegisterBankAccountCommand command) {

        MembershipStatus membershipStatus = getMembershipPort.getMembershipStatus(command.getMembershipId());
        if(!membershipStatus.isValid()){
            return null;
        }

        //todo - 중복등록 체크 로직 추가 -> 중복이면 throw exception

        BankAccount bankAccount = requestBankAccountIntoPort.getBankAccountInfo(
            new GetBankAccountRequest(command.getBankName(), command.getBankAccountNumber())
        );

        boolean accountIsValid = bankAccount.isValid();
        if(accountIsValid){
            var saved = registerBankAccountPort.createRegisteredBankAccount(
                new RegisteredBankAccount.MembershipId(command.getMembershipId()),
                new RegisteredBankAccount.BankName(command.getBankName()),
                new RegisteredBankAccount.BankAccountNumber(command.getBankAccountNumber()),
                new RegisteredBankAccount.LinkedStatusIsValid(command.isValid()),
                new RegisteredBankAccount.AggregateIdentifier("")
            );
            return registeredBankAccountMapper.mapToDomainEntity(saved);
        }else{
            return null;
        }

    }

    @Override
    public void registerBankAccountByEvent(RegisterBankAccountCommand command) {
        commandGateway.send(new CreateRegisteredBankAccountCommand(
                command.getMembershipId(),
                command.getBankName(),
                command.getBankAccountNumber())
        ).whenComplete((result, throwable) -> {
            if(throwable != null){
                throwable.printStackTrace();
            }else{
                //정상적인 이벤트 소싱
                registerBankAccountPort.createRegisteredBankAccount(
                        new RegisteredBankAccount.MembershipId(command.getMembershipId()),
                        new RegisteredBankAccount.BankName(command.getBankName()),
                        new RegisteredBankAccount.BankAccountNumber(command.getBankAccountNumber()),
                        new RegisteredBankAccount.LinkedStatusIsValid(command.isValid()),
                        new RegisteredBankAccount.AggregateIdentifier(result.toString())
                );
            }
        });

    }

    @Override
    public RegisteredBankAccount findBankAccount(FindBankAccountCommand command) {
        var entity = findBankAccountPort.findBankAccount(command);
        return registeredBankAccountMapper.mapToDomainEntity(entity);
    }

    @Override
    public RegisteredBankAccount getRegisteredBankAccount(GetRegisteredBankAccountCommand command) {
        var entity = getRegisteredBankAccountPort.getRegisteredBankAccount(command);
        return registeredBankAccountMapper.mapToDomainEntity(entity);
    }
}
