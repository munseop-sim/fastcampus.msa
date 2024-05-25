package ms2709.payservice.money.application.port.in;

import ms2709.payservice.money.domain.MoneyChangingRequest;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class IncreaseMoneyRequestUseCase
 * @since 2024-05-16 오전 8:29
 */
public interface IncreaseMoneyRequestUseCase {
    MoneyChangingRequest increaseMoneyRequest(IncreaseMoneyRequestCommand command);
    MoneyChangingRequest increaseMoneyRequestAsync(IncreaseMoneyRequestCommand command);
    void increaseMoneyRequestByEvent(IncreaseMoneyRequestCommand command);
}
