package com.ms2709.banking.adapter.out.persistence;

import com.ms2709.banking.adapter.out.persistence.entity.RegisteredBankAccountJpaEntity;
import com.ms2709.banking.adapter.out.persistence.repository.RegisteredBankAccountJpaEntityRepository;
import com.ms2709.banking.application.port.out.RegisterBankAccountPort;
import com.ms2709.banking.domain.RegisteredBankAccount;
import lombok.RequiredArgsConstructor;
import ms2709.global.PersistenceAdapter;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class RegisteredBankAccountPersistenceAdapter
 * @since 2024-05-13 오후 10:17
 */
@RequiredArgsConstructor
@PersistenceAdapter
public class RegisteredBankAccountPersistenceAdapter implements RegisterBankAccountPort {
    private final RegisteredBankAccountJpaEntityRepository bankAccountRepository;
    @Override
    public RegisteredBankAccountJpaEntity createRegisteredBankAccount(RegisteredBankAccount.MembershipId membershipId, RegisteredBankAccount.BankName bankName, RegisteredBankAccount.BankAccountNumber bankAccountNumber, RegisteredBankAccount.LinkedStatusIsValid linkedStatusIsValid) {
        return bankAccountRepository.save(
                new RegisteredBankAccountJpaEntity(
                        membershipId.value(),
                        bankName.value(),
                        bankAccountNumber.value(),
                        linkedStatusIsValid.value()
                )
        );
    }
}
