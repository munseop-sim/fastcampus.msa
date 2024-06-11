package ms2709.payservice.money.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 *  IncreaseMoneyChangingRequest
 * @since 2024-05-16 오전 8:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncreaseMoneyChangingRequest {
    private String targetMembershipId;

    // 무조건 증액 요청 (충전)

    private int amount;
}
