package ms2709.payservice.money.application.port.in;

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
 *  CreateMemberMoneyCommand
 * @since 2024-05-25 오후 1:32
 */
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class CreateMemberMoneyCommand extends SelfValidating<CreateMemberMoneyCommand> {

    @NotNull
    private final String targetMembershipId;

    public CreateMemberMoneyCommand(@NotNull String targetMembershipId) {
        this.targetMembershipId = targetMembershipId;
        this.validateSelf();
    }
}