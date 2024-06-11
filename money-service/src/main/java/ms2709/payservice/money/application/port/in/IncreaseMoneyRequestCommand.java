package ms2709.payservice.money.application.port.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ms2709.global.SelfValidating;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 *  IncreaseMoneyRequestCommand
 * @since 2024-05-16 오전 8:28
 */

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class IncreaseMoneyRequestCommand extends SelfValidating<IncreaseMoneyRequestCommand> {

    @NotBlank
    private final String targetMembershipId;

    @NotNull
    private final int amount;

    public IncreaseMoneyRequestCommand(String targetMembershipId, int amount) {
        this.targetMembershipId = targetMembershipId;
        this.amount = amount;
        this.validateSelf();
    }
}
