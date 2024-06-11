package ms2709.payservice.money.adapter.axon.command;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 *  IncreaseMoneyRequestEventCommand
 * @since 2024-05-25 오후 2:12
 */
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class IncreaseMoneyRequestEventCommand {
    @NotNull
    @TargetAggregateIdentifier
    private String aggregateIdentifier;

    @NotNull
    private final String targetMembershipId;

    @NotNull
    private final int amount;


}