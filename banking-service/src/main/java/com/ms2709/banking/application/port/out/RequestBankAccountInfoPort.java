package com.ms2709.banking.application.port.out;

import com.ms2709.banking.adapter.out.external.bank.BankAccount;
import com.ms2709.banking.adapter.out.external.bank.GetBankAccountRequest;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class RequestBankAccountInfoPort
 * @since 2024-05-15 오전 7:14
 */
public interface RequestBankAccountInfoPort {
    BankAccount getBankAccountInfo(GetBankAccountRequest request);
}
