package ms2709.payservice.banking.application.port.in;

import ms2709.payservice.banking.domain.RegisteredBankAccount;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class RegisterBankAccountUseCase
 * @since 2024-05-13 오후 11:20
 */
public interface RegisterBankAccountUseCase {
    RegisteredBankAccount registerBankAccount(RegisterBankAccountCommand command);
}
