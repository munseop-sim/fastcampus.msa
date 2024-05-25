package ms2709.payservice.banking.adapter.in.web;

import lombok.RequiredArgsConstructor;
import ms2709.global.WebAdapter;
import ms2709.payservice.banking.application.port.in.GetRegisteredBankAccountCommand;
import ms2709.payservice.banking.application.port.in.GetRegisteredBankAccountUseCase;
import ms2709.payservice.banking.domain.RegisteredBankAccount;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/banking")
public class GetRegisteredBankAccountController {
    private final GetRegisteredBankAccountUseCase getRegisteredBankAccountUseCase;

    @GetMapping(path = "/account/{membershipId}")
    RegisteredBankAccount getRegisteredBankAccount(@PathVariable String membershipId) {
        // 편의상 사용.
        GetRegisteredBankAccountCommand command = GetRegisteredBankAccountCommand.builder().membershipId(membershipId).build();
        return getRegisteredBankAccountUseCase.getRegisteredBankAccount(command);
    }
}
