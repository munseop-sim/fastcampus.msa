package ms2709.payservice.banking.adapter.axon.command;

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
 *  UpdateRequestFirmbankingCommand
 * @since 2024-05-25 오후 3:06
 */
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class UpdateRequestFirmbankingCommand {
    @NotNull
    @TargetAggregateIdentifier
    private String aggregateIdentifier;

    private final int firmbankingStatus;
}