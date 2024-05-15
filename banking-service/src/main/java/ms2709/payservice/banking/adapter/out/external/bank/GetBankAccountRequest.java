package ms2709.payservice.banking.adapter.out.external.bank;

import lombok.Data;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class GetBankAccountRequest
 * @since 2024-05-15 오전 7:33
 */
@Data
public class GetBankAccountRequest {
    private String bankName;
    private String bankAccountNumber;

    public GetBankAccountRequest(String bankAccountNumber, String bankName) {
        this.bankAccountNumber = bankAccountNumber;
        this.bankName = bankName;
    }
}
