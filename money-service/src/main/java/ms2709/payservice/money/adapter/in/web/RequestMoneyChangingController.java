package ms2709.payservice.money.adapter.in.web;

import lombok.RequiredArgsConstructor;
import ms2709.global.WebAdapter;
import ms2709.payservice.money.application.port.in.CreateMemberMoneyCommand;
import ms2709.payservice.money.application.port.in.CreateMemberMoneyUseCase;
import ms2709.payservice.money.application.port.in.IncreaseMoneyRequestCommand;
import ms2709.payservice.money.application.port.in.IncreaseMoneyRequestUseCase;
import ms2709.payservice.money.domain.MoneyChangingRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class RequestMoneyChangingController
 * @since 2024-05-16 오전 8:25
 */
@WebAdapter
@RestController
@RequestMapping("/money")
@RequiredArgsConstructor
public class RequestMoneyChangingController {
    private final IncreaseMoneyRequestUseCase increaseMoneyRequestUseCase;
    private final CreateMemberMoneyUseCase createMemberMoneyUseCase;

    @PostMapping(path = "/money/increase")
    MoneyChangingResultDetail increaseMoneyChangingRequest(@RequestBody IncreaseMoneyChangingRequest request) {
        IncreaseMoneyRequestCommand command = IncreaseMoneyRequestCommand.builder()
                .targetMembershipId(request.getTargetMembershipId())
                .amount(request.getAmount())
                .build();

        MoneyChangingRequest moneyChangingRequest = increaseMoneyRequestUseCase.increaseMoneyRequest(command);

        // MoneyChangingRequest -> MoneyChangingResultDetail
        MoneyChangingResultDetail resultDetail = new MoneyChangingResultDetail(
                moneyChangingRequest.getMoneyChangingRequestId(),
                moneyChangingRequest.getChangingType(),
                moneyChangingRequest.getChangingMoneyStatus(),
                moneyChangingRequest.getChangingMoneyAmount());
        return resultDetail;
    }

    @PostMapping(path = "/money/increase/async")
    MoneyChangingResultDetail increaseMoneyChangingRequestAsync(@RequestBody IncreaseMoneyChangingRequest request) {
        IncreaseMoneyRequestCommand command = IncreaseMoneyRequestCommand.builder()
                .targetMembershipId(request.getTargetMembershipId())
                .amount(request.getAmount())
                .build();

        MoneyChangingRequest moneyChangingRequest = increaseMoneyRequestUseCase.increaseMoneyRequestAsync(command);

        // MoneyChangingRequest -> MoneyChangingResultDetail
        MoneyChangingResultDetail resultDetail = new MoneyChangingResultDetail(
                moneyChangingRequest.getMoneyChangingRequestId(),
                moneyChangingRequest.getChangingType(),
                moneyChangingRequest.getChangingMoneyStatus(),
                moneyChangingRequest.getChangingMoneyAmount());
        return resultDetail;
    }
    @PostMapping(path = "/money/create-member-money")
    void createMemberMoney(@RequestBody CreateMemberMoneyRequest request) {
        createMemberMoneyUseCase.createMemberMoney(new CreateMemberMoneyCommand(request.getTargetMembershipId()));
    }

    @PostMapping(path = "/money/increase-eda")
    void increaseMoneyChangingRequestByEvent(@RequestBody IncreaseMoneyChangingRequest request) {
        IncreaseMoneyRequestCommand command = IncreaseMoneyRequestCommand.builder()
                .targetMembershipId(request.getTargetMembershipId())
                .amount(request.getAmount())
                .build();

        increaseMoneyRequestUseCase.increaseMoneyRequestByEvent(command);
    }

}
