package ms2709.payservice.money.application.port.out;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 *  ExternalBankingPort
 * @since 2024-05-16 오후 9:50
 */
public interface ExternalBankingPort {
    Boolean increaseAccountMoney(String membershipId, int amount);
}
