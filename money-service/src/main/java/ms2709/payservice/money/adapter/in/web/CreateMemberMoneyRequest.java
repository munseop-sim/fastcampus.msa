package ms2709.payservice.money.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class CreateMemberMoneyRequest
 * @since 2024-05-25 오후 1:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMemberMoneyRequest {
    private String targetMembershipId;
}
