package ms2709.payservice.money.application.port.in;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class CreateMemberMoneyUseCase
 * @since 2024-05-25 오후 1:35
 */
public interface CreateMemberMoneyUseCase {
    void createMemberMoney(CreateMemberMoneyCommand command);
}
