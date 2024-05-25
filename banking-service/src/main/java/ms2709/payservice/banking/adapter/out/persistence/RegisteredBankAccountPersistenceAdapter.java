package ms2709.payservice.banking.adapter.out.persistence;

import ms2709.payservice.banking.adapter.out.persistence.entity.RegisteredBankAccountJpaEntity;
import ms2709.payservice.banking.adapter.out.persistence.repository.RegisteredBankAccountJpaEntityRepository;
import ms2709.payservice.banking.application.port.in.FindBankAccountCommand;
import ms2709.payservice.banking.application.port.in.GetRegisteredBankAccountCommand;
import ms2709.payservice.banking.application.port.out.FindBankAccountPort;
import ms2709.payservice.banking.application.port.out.GetRegisteredBankAccountPort;
import ms2709.payservice.banking.application.port.out.RegisterBankAccountPort;
import ms2709.payservice.banking.domain.RegisteredBankAccount;
import lombok.RequiredArgsConstructor;
import ms2709.global.PersistenceAdapter;

import java.util.List;

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
public class RegisteredBankAccountPersistenceAdapter implements RegisterBankAccountPort, FindBankAccountPort , GetRegisteredBankAccountPort {
    private final RegisteredBankAccountJpaEntityRepository bankAccountRepository;

    @Override
    public RegisteredBankAccountJpaEntity findBankAccount(FindBankAccountCommand findBankAccountCommand) {
        var accountId = findBankAccountCommand.getRegisteredBankAccountId();
        var entity =bankAccountRepository.findById(accountId).orElse(null);
        if(entity == null) throw new RuntimeException("Bank account not found");
        return entity;
    }

    @Override
    public RegisteredBankAccountJpaEntity getRegisteredBankAccount(GetRegisteredBankAccountCommand command) {
        List<RegisteredBankAccountJpaEntity> entityList = bankAccountRepository.findByMembershipId(command.getMembershipId());
        if (!entityList.isEmpty()) {
            return entityList.get(0);
        }
        return null;
    }

    @Override
    public RegisteredBankAccountJpaEntity createRegisteredBankAccount(RegisteredBankAccount.MembershipId membershipId, RegisteredBankAccount.BankName bankName, RegisteredBankAccount.BankAccountNumber bankAccountNumber, RegisteredBankAccount.LinkedStatusIsValid linkedStatusIsValid, RegisteredBankAccount.AggregateIdentifier aggregateIdentifier) {
        return bankAccountRepository.save(
                new RegisteredBankAccountJpaEntity(
                        membershipId.value(),
                        bankName.value(),
                        bankAccountNumber.value(),
                        linkedStatusIsValid.value(),
                        aggregateIdentifier.value()
                )
        );
    }
}
