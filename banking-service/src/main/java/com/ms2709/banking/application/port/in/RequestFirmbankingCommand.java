package com.ms2709.banking.application.port.in;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ms2709.global.SelfValidating;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class FirmbankingRequestCommnad
 * @since 2024-05-15 오전 10:57
 */

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class RequestFirmbankingCommand extends SelfValidating<RequestFirmbankingCommand> {
    @NotNull
    private final String fromBankName;
    @NotNull
    private final String fromBankAccountNumber;
    @NotNull
    private final String toBankName;
    @NotNull
    private final String toBankAccountNumber;
    @Positive
    private final Integer moneyAmount;

    public RequestFirmbankingCommand(String fromBankName, String fromBankAccountNumber, String toBankName, String toBankAccountNumber, Integer moneyAmount) {
        this.fromBankName = fromBankName;
        this.fromBankAccountNumber = fromBankAccountNumber;
        this.toBankName = toBankName;
        this.toBankAccountNumber = toBankAccountNumber;
        this.moneyAmount = moneyAmount;

        this.validateSelf();
    }
}
