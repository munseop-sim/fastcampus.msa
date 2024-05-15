package com.ms2709.banking.adapter.in.web;

import com.ms2709.banking.application.port.in.FindRequestFirmbankingCommand;
import com.ms2709.banking.application.port.in.FindRequestFirmbankingUseCase;
import com.ms2709.banking.domain.FirmbankingRequest;
import lombok.RequiredArgsConstructor;
import ms2709.global.WebAdapter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class FindRequestFirmbankingController
 * @since 2024-05-15 오후 1:36
 */
@WebAdapter
@RestController
@RequestMapping("/banking")
@RequiredArgsConstructor
public class FindRequestFirmbankingController {
    private final FindRequestFirmbankingUseCase findRequestFirmbankingUseCase;

    @GetMapping("/firmbanking/{firmbankingRequestId}")
    FirmbankingRequest findById(@PathVariable String firmbankingRequestId) {
        var command = new FindRequestFirmbankingCommand(
            Long.parseLong(firmbankingRequestId)
        );


        return findRequestFirmbankingUseCase.findRequestFirmbanking(command);
    }


}
