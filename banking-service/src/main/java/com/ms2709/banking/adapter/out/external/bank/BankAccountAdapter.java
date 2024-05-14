package com.ms2709.banking.adapter.out.external.bank;

import com.ms2709.banking.application.port.out.RequestBankAccountInfoPort;
import lombok.RequiredArgsConstructor;
import ms2709.global.ExternalSystemAdapter;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class BankAccountAdapter
 * @since 2024-05-15 오전 7:13
 */
@ExternalSystemAdapter
@RequiredArgsConstructor
public class BankAccountAdapter implements RequestBankAccountInfoPort {

    @Override
    public BankAccount getBankAccountInfo(GetBankAccountRequest request) {
        //실제로 외부 은행에서 http요청을 통해서
        //실제 은행계좌의 정보를 가져오는 로직이 들어갈 것이다.
        //
        //실제은행 계좌 -> BankAccount
        return new BankAccount(request.getBankName(), request.getBankAccountNumber(), true);
    }
}
