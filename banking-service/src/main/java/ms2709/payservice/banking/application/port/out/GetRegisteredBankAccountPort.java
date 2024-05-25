package ms2709.payservice.banking.application.port.out;

import ms2709.payservice.banking.adapter.out.persistence.entity.RegisteredBankAccountJpaEntity;
import ms2709.payservice.banking.application.port.in.GetRegisteredBankAccountCommand;

public interface GetRegisteredBankAccountPort {
    RegisteredBankAccountJpaEntity getRegisteredBankAccount(GetRegisteredBankAccountCommand command);
}