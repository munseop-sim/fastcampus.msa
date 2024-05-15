package ms2709.payservice.banking.adapter.in.web;

import ms2709.payservice.banking.application.port.in.FindBankAccountCommand;
import ms2709.payservice.banking.application.port.in.FindBankAccountUseCase;
import ms2709.payservice.banking.domain.RegisteredBankAccount;
import lombok.RequiredArgsConstructor;
import ms2709.global.WebAdapter;
import org.springframework.web.bind.annotation.*;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class FindBankAccountController
 * @since 2024-05-15 오전 10:38
 */
@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/banking")
public class FindBankAccountController {
    private final FindBankAccountUseCase findBankAccountUseCase;

    @GetMapping("/{registeredBankAccountId}")
    RegisteredBankAccount findById(@PathVariable String registeredBankAccountId) {
        var accountId = Long.parseLong(registeredBankAccountId);
        return findBankAccountUseCase.findBankAccount(new FindBankAccountCommand(accountId));
    }
}
