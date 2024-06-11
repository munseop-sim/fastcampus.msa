package ms2709.payservice.money.adapter.out.external.banking;

import ms2709.global.ExternalSystemAdapter;
import ms2709.payservice.money.application.port.out.ExternalBankingPort;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 *  ExternalBankingAdapter
 * @since 2024-05-16 오후 9:51
 */
@ExternalSystemAdapter
public class ExternalBankingAdapter implements ExternalBankingPort {
    @Override
    public Boolean increaseAccountMoney(String membershipId, int amount) {
        // 펌뱅킹 시스템과 연동하여 계좌에 돈을 입금하는 로직
        // 테스트 상황이므로 성공했다고 가정한다.
        return true;
    }
}
