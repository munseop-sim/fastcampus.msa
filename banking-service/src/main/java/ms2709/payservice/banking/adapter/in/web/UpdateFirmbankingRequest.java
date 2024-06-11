package ms2709.payservice.banking.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 *  UpdateFirmbankingRequest
 * @since 2024-05-25 오후 3:46
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateFirmbankingRequest {
    private String firmbankingAggregateIdentifier;
    private int status;
}