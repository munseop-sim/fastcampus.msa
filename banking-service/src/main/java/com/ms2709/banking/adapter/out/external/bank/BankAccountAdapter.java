package com.ms2709.banking.adapter.out.external.bank;

import com.ms2709.banking.application.port.out.RequestBankAccountInfoPort;
import com.ms2709.banking.application.port.out.RequestExternalFirmbankingPort;
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
public class BankAccountAdapter implements RequestBankAccountInfoPort, RequestExternalFirmbankingPort {

    @Override
    public BankAccount getBankAccountInfo(GetBankAccountRequest request) {
        //실제로 외부 은행에서 http요청을 통해서
        //실제 은행계좌의 정보를 가져오는 로직이 들어갈 것이다.
        //
        //실제은행 계좌 -> BankAccount
        return new BankAccount(request.getBankName(), request.getBankAccountNumber(), true);
    }

    @Override
    public FirmbankingResult requestExternalFirmbanking(ExternalFirmbankingRequest firmbankingRequest) {
        // 실제로 외부 은행에 http를 통해서 펌뱅킹 요청을 수행하고 그 결과를 받아오는 로직이 들어갈 것이다.
        // 펌뱅킹 요청결과를 FirmbankingResult으로 파싱해서 리턴한다.
        return new FirmbankingResult(0);
    }
}
