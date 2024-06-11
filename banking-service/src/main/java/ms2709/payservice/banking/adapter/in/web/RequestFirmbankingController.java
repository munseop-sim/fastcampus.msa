package ms2709.payservice.banking.adapter.in.web;

import ms2709.payservice.banking.adapter.axon.command.UpdateRequestFirmbankingCommand;
import ms2709.payservice.banking.application.port.in.RequestFirmbankingCommand;
import ms2709.payservice.banking.application.port.in.RequestFirmbankingUseCase;
import ms2709.payservice.banking.domain.FirmbankingRequest;
import lombok.RequiredArgsConstructor;
import ms2709.global.WebAdapter;
import org.springframework.web.bind.annotation.*;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 *  RequestFirmbankingController
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


    @PostMapping(path = "/firmbanking/request-eda")
    void requestFirmbankingByEvent(@RequestBody RequestFirmbankingRequest request) {
        RequestFirmbankingCommand command = RequestFirmbankingCommand.builder()
                .toBankName(request.getToBankName())
                .toBankAccountNumber(request.getToBankAccountNumber())
                .fromBankName(request.getFromBankName())
                .fromBankAccountNumber(request.getFromBankAccountNumber())
                .moneyAmount(request.getMoneyAmount())
                .build();

        requestFirmbankingUseCase.requestFirmbankingByEvent(command);
    }

    @PutMapping(path = "/firmbanking/update-eda")
    void updateFirmbankingByEvent(@RequestBody UpdateFirmbankingRequest request) {
        UpdateRequestFirmbankingCommand command =UpdateRequestFirmbankingCommand.builder().aggregateIdentifier(request.getFirmbankingAggregateIdentifier()).firmbankingStatus(request.getStatus()).build();
        requestFirmbankingUseCase.updateFirmbankingByEvent(command);
    }
}
