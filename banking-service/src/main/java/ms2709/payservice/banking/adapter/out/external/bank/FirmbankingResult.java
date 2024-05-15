package ms2709.payservice.banking.adapter.out.external.bank;

import lombok.Data;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class FirmbankingResult
 * @since 2024-05-15 오전 11:54
 */
@Data
public class FirmbankingResult {
    private int resultCode; //0: 성공, 1: 실패

    public FirmbankingResult(int resultCode) {
        this.resultCode = resultCode;
    }
}
