package com.ms2709.banking.adapter.out.external.bank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class ExternalFirmbankingRequest
 * @since 2024-05-15 오전 11:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExternalFirmbankingRequest {
    private String fromBankName;
    private String fromBankAccountNumber;
    private String toBankName;
    private String toBankAccountNumber;
}
