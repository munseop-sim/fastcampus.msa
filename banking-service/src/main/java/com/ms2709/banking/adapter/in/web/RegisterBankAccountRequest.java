package com.ms2709.banking.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * RegisteredBankAccount를 등록하기 위한 Web 요청 클래스
 *
 * @author 심문섭
 * @version 1.0
 * @class RegisterBankAccountRequest
 * @since 2024-05-13 오후 10:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterBankAccountRequest {
    private String membershipId;
    private String bankName;
    private String bankAccountNumber;
    private boolean isValid;
}
