package ms2709.payservice.money.adapter.axon.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 *  MemberMoneyCreateEvent
 * @since 2024-05-25 오후 1:44
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberMoneyCreateEvent {
    private String membershipId;
}