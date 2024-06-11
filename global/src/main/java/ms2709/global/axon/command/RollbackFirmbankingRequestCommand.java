package ms2709.global.axon.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 *  RollbackFirmbankingRequestCommand
 * @modified
 * @since 2024-05-28 오전 8:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RollbackFirmbankingRequestCommand {
    private String rollbackFirmbankingId;
    @TargetAggregateIdentifier
    private String aggregateIdentifier;
    private String rechargeRequestId;
    private String membershipId;
    private String bankName;
    private String bankAccountNumber;
    private int moneyAmount;
}