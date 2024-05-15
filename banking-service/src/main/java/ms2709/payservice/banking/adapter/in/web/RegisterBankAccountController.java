package ms2709.payservice.banking.adapter.in.web;

import ms2709.payservice.banking.application.port.in.RegisterBankAccountCommand;
import ms2709.payservice.banking.application.port.in.RegisterBankAccountUseCase;
import ms2709.payservice.banking.domain.RegisteredBankAccount;
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
 * @class RegisterBankAccountController
 * @since 2024-05-13 오후 10:14
 */
@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/banking")
public class RegisterBankAccountController {

    private final RegisterBankAccountUseCase registerBankAccountUseCase;

    @PostMapping("/account/register")
    RegisteredBankAccount registeredBankAccount(@RequestBody RegisterBankAccountRequest request) {
        var command = RegisterBankAccountCommand.builder()
                .membershipId(request.getMembershipId())
                .bankName(request.getBankName())
                .bankAccountNumber(request.getBankAccountNumber())
                .isValid(request.isValid())
                .build();
        var saved = registerBankAccountUseCase.registerBankAccount(command);
        if(saved == null){
            // todo - error handling
            return null;
        }

        return saved;
    }

}
