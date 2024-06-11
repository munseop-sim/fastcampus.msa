package ms2709.payservice.banking.application.port.out;

import ms2709.payservice.banking.adapter.out.external.bank.BankAccount;
import ms2709.payservice.banking.adapter.out.external.bank.GetBankAccountRequest;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 *  RequestBankAccountInfoPort
 * @since 2024-05-15 오전 7:14
 */
public interface RequestBankAccountInfoPort {
    BankAccount getBankAccountInfo(GetBankAccountRequest request);
}
