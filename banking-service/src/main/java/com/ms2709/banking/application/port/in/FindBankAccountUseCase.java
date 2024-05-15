package com.ms2709.banking.application.port.in;

import com.ms2709.banking.domain.RegisteredBankAccount;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class FindBankAccountUseCase
 * @since 2024-05-15 오전 10:39
 */
public interface FindBankAccountUseCase {
    RegisteredBankAccount findBankAccount(FindBankAccountCommand command);
}
