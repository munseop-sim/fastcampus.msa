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
 *  RequestFirmbankingCommand
 * @modified
 * @since 2024-05-28 오전 8:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestFirmbankingCommand {
    private String requestFirmbankingId;
    @TargetAggregateIdentifier
    private String aggregateIdentifier;
    private String rechargeRequestId;
    private String membershipId;
    private String fromBankName;
    private String fromBankAccountNumber;
    private String toBankName;
    private String toBankAccountNumber;
    private int moneyAmount; // only won
}