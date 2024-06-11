package ms2709.global.axon.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 *  RollbackFirmbankingFinishedEvent
 * @modified
 * @since 2024-05-28 오전 8:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RollbackFirmbankingFinishedEvent {
    private String rollbackFirmbankingId;
    private String membershipId;
    private String rollbackFirmbankingAggregateIdentifier;
}