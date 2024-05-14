package com.ms2709.banking.application.service;

import com.ms2709.banking.adapter.out.external.bank.BankAccount;
import com.ms2709.banking.adapter.out.external.bank.GetBankAccountRequest;
import com.ms2709.banking.adapter.out.persistence.RegisteredBankAccountMapper;
import com.ms2709.banking.application.port.in.RegisterBankAccountCommand;
import com.ms2709.banking.application.port.in.RegisterBankAccountUseCase;
import com.ms2709.banking.application.port.out.RegisterBankAccountPort;
import com.ms2709.banking.application.port.out.RequestBankAccountInfoPort;
import com.ms2709.banking.domain.RegisteredBankAccount;
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
public class RegisterBankAccountService implements RegisterBankAccountUseCase {

    private final RegisterBankAccountPort registerBankAccountPort;
    private final RequestBankAccountInfoPort requestBankAccountIntoPort;
    private final RegisteredBankAccountMapper registeredBankAccountMapper;
    @Override
    public RegisteredBankAccount registerBankAccount(RegisterBankAccountCommand command) {
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
}
