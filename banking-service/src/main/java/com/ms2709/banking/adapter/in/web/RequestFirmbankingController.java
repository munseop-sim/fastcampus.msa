package com.ms2709.banking.adapter.in.web;

import com.ms2709.banking.application.port.in.RequestFirmbankingCommand;
import com.ms2709.banking.application.port.in.RequestFirmbankingUseCase;
import com.ms2709.banking.domain.FirmbankingRequest;
import lombok.RequiredArgsConstructor;
import ms2709.global.WebAdapter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class RequestFirmbankingController
 * @since 2024-05-15 오후 1:26
 */
@WebAdapter
@RestController
@RequestMapping("/banking")
@RequiredArgsConstructor
public class RequestFirmbankingController {
    private final RequestFirmbankingUseCase requestFirmbankingUseCase;

    @PostMapping("/firmbanking/request")
    FirmbankingRequest requestFirmbanking(@RequestBody RequestFirmbankingRequest request) {
        var command = RequestFirmbankingCommand.builder()
                .toBankName(request.getToBankName())
                .toBankAccountNumber(request.getToBankAccountNumber())
                .moneyAmount(request.getMoneyAmount())
                .fromBankName(request.getFromBankName())
                .fromBankAccountNumber(request.getFromBankAccountNumber())
                .moneyAmount(request.getMoneyAmount())
                .build();

        return requestFirmbankingUseCase.requestFirmbanking(command);
    }
}
