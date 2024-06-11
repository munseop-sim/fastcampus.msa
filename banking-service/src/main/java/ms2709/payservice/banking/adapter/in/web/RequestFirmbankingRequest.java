package ms2709.payservice.banking.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 *  RequestFirmbankingRequest
 * @since 2024-05-15 오전 10:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestFirmbankingRequest {

    // a ->  b 실물계좌라 요청을 하기 위한 Request

    private String fromBankName;

    private String fromBankAccountNumber;

    private String toBankName;

    private String toBankAccountNumber;

    private Integer moneyAmount;
}
