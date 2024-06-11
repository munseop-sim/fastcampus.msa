package ms2709.payservice.banking.adapter.in.web;

import ms2709.payservice.banking.application.port.in.FindRequestFirmbankingCommand;
import ms2709.payservice.banking.application.port.in.FindRequestFirmbankingUseCase;
import ms2709.payservice.banking.domain.FirmbankingRequest;
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
 *  FindRequestFirmbankingController
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
