package ms2709.payservice.banking.application.port.in;

import ms2709.payservice.banking.domain.RegisteredBankAccount;

public interface GetRegisteredBankAccountUseCase {
    RegisteredBankAccount getRegisteredBankAccount(GetRegisteredBankAccountCommand command);
}