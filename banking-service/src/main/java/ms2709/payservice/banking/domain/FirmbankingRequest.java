package ms2709.payservice.banking.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class FirmbankingReqeust
 * @since 2024-05-15 오전 7:16
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FirmbankingRequest {
    @Getter
    private final String firmbankingRequestId;

    @Getter
    private final String fromBankName;

    @Getter
    private final String fromBankAccountNumber;

    @Getter
    private final String toBankName;

    @Getter
    private final String toBankAccountNumber;

    @Getter
    private final Integer moneyAmount;

    @Getter
    private final Integer firmBankingStatus;

    @Getter
    private final UUID uuid;

    public static FirmbankingRequest create(
            FirmbankingRequestId firmbankingRequestId,
            FromBankName fromBankName,
            FromBankAccountNumber fromBankAccountNumber,
            ToBankName toBankName,
            ToBankAccountNumber toBankAccountNumber,
            MoneyAmount moneyAmount,
            FirmBankingStatus firmBankingStatus,
            UUID uuid
    ) {
        return new FirmbankingRequest(
                firmbankingRequestId.value,
                fromBankName.value,
                fromBankAccountNumber.value,
                toBankName.value,
                toBankAccountNumber.value,
                moneyAmount.value,
                firmBankingStatus.value,
                uuid
        );
    }

    public record FirmbankingRequestId(String value){}
    public record FromBankName(String value){}
    public record FromBankAccountNumber(String value){}
    public record ToBankName(String value){}
    public record ToBankAccountNumber(String value){}
    public record MoneyAmount(Integer value){}
    public record FirmBankingStatus(Integer value){}
}
