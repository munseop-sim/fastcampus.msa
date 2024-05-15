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
 * @class FindRequestFirmbankingCommand
 * @since 2024-05-15 오후 1:38
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class FindRequestFirmbankingCommand extends SelfValidating<FindRequestFirmbankingCommand> {
    @NotNull
    private final Long firmbankingRequestId;

    public FindRequestFirmbankingCommand(Long firmbankingRequestId) {
        this.firmbankingRequestId = firmbankingRequestId;
        this.validateSelf();
    }

}
