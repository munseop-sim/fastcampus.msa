package ms2709.global.axon.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 *  RequestFirmbankingFinishedEvent
 * @modified
 * @since 2024-05-28 오전 8:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestFirmbankingFinishedEvent {
    private String requestFirmbankingId;
    private String rechargingRequestId;
    private String membershipId;
    private String toBankName;
    private String toBankAccountNumber;
    private int moneyAmount; // only won
    private int status;
    private String requestFirmbankingAggregateIdentifier;
}