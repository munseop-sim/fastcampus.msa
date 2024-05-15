package ms2709.payservice.banking.application.service;

import ms2709.payservice.banking.adapter.out.external.bank.BankAccount;
import ms2709.payservice.banking.adapter.out.external.bank.GetBankAccountRequest;
import ms2709.payservice.banking.adapter.out.persistence.RegisteredBankAccountMapper;
import ms2709.payservice.banking.application.port.in.FindBankAccountCommand;
import ms2709.payservice.banking.application.port.in.FindBankAccountUseCase;
import ms2709.payservice.banking.application.port.in.RegisterBankAccountCommand;
import ms2709.payservice.banking.application.port.in.RegisterBankAccountUseCase;
import ms2709.payservice.banking.application.port.out.FindBankAccountPort;
import ms2709.payservice.banking.application.port.out.RegisterBankAccountPort;
import ms2709.payservice.banking.application.port.out.RequestBankAccountInfoPort;
import ms2709.payservice.banking.domain.RegisteredBankAccount;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import ms2709.global.UseCase;

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
public class RegisterBankAccountService implements RegisterBankAccountUseCase, FindBankAccountUseCase {

    private final RegisterBankAccountPort registerBankAccountPort;
    private final FindBankAccountPort findBankAccountPort;
    private final RequestBankAccountInfoPort requestBankAccountIntoPort;
    private final RegisteredBankAccountMapper registeredBankAccountMapper;
    @Override
    public RegisteredBankAccount registerBankAccount(RegisterBankAccountCommand command) {
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
                new RegisteredBankAccount.LinkedStatusIsValid(command.isValid())
            );
            return registeredBankAccountMapper.mapToDomainEntity(saved);
        }else{
            return null;
        }

    }

    @Override
    public RegisteredBankAccount findBankAccount(FindBankAccountCommand command) {
        var entity = findBankAccountPort.findBankAccount(command);
        return registeredBankAccountMapper.mapToDomainEntity(entity);
    }
}
