package ms2709.payservice.banking.application.port.in;

import lombok.Builder;
import lombok.Getter;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 *  RegisterBankAccountCommand
 * @since 2024-05-13 오후 10:28
 */
@Builder
public class RegisterBankAccountCommand {
    @Getter
    private final String membershipId;
    @Getter
    private final String bankName;
    @Getter
    private final String bankAccountNumber;
    @Getter
    private final boolean isValid;

}
