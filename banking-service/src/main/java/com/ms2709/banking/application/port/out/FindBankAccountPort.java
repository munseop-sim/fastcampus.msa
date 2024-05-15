package com.ms2709.banking.application.port.out;

import com.ms2709.banking.adapter.out.persistence.entity.RegisteredBankAccountJpaEntity;
import com.ms2709.banking.application.port.in.FindBankAccountCommand;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class FindBankAccountPort
 * @since 2024-05-15 오전 10:40
 */
public interface FindBankAccountPort {
    RegisteredBankAccountJpaEntity findBankAccount(FindBankAccountCommand findBankAccountCommand);
}
