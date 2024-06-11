package ms2709.payservice.banking.application.port.in;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ms2709.global.SelfValidating;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 *  FindBankAccountCommand
 * @since 2024-05-15 오전 10:42
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class FindBankAccountCommand extends SelfValidating<FindBankAccountCommand> {

    @NotNull
    private final Long registeredBankAccountId;

    public FindBankAccountCommand(Long registeredBankAccountId) {
        this.registeredBankAccountId = registeredBankAccountId;
        this.validateSelf();
    }
}
