package ms2709.payservice.banking.adapter.out.external.bank;

import lombok.Data;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 *  BankAccount
 * @since 2024-05-15 오전 7:32
 */
@Data
public class BankAccount {
    private String bankName;
    private String bankAccountNumber;

    private boolean isValid;

    public BankAccount(String bankName, String bankAccountNumber, boolean isValid) {
        this.bankName = bankName;
        this.bankAccountNumber = bankAccountNumber;
        this.isValid = isValid;
    }
}
