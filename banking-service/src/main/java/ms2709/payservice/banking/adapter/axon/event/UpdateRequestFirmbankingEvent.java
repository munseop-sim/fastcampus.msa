package ms2709.payservice.banking.adapter.axon.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 *  UpdateRequestFirmbankingEvent
 * @since 2024-05-25 오후 3:09
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateRequestFirmbankingEvent {

    private int firmbankingStatus;
}