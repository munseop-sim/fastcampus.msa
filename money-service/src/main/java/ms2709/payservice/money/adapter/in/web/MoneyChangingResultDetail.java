package ms2709.payservice.money.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ms2709.payservice.money.domain.enums.MoneyChangingRequestStatusTypes;
import ms2709.payservice.money.domain.enums.MoneyChangingTypes;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class MoneyChangingResultDetail
 * @since 2024-05-16 오전 8:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoneyChangingResultDetail {
    private String moneyChangingRequestId;

    private MoneyChangingTypes moneyChangingType;
    private MoneyChangingRequestStatusTypes moneyChangingResultStatus;
    private Integer amount;
}
