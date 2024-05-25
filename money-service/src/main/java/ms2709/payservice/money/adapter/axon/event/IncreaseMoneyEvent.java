package ms2709.payservice.money.adapter.axon.event;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ms2709.global.SelfValidating;
import ms2709.payservice.money.application.port.in.IncreaseMoneyRequestCommand;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class IncreaseMoneyEvent
 * @since 2024-05-25 오후 2:11
 */

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class IncreaseMoneyEvent extends SelfValidating<IncreaseMoneyRequestCommand> {

    @NotNull
    private final String aggregateIdentifier;

    @NotNull
    private final String targetMembershipId;

    @NotNull
    private final int amount;

    public IncreaseMoneyEvent(String aggregateIdentifier, String targetMembershipId, int amount) {
        this.aggregateIdentifier = aggregateIdentifier;
        this.targetMembershipId = targetMembershipId;
        this.amount = amount;
    }
}