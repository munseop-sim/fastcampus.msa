package ms2709.payservice.money.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import ms2709.payservice.money.domain.enums.MoneyChangingRequestStatusTypes;
import ms2709.payservice.money.domain.enums.MoneyChangingTypes;

import java.time.LocalDate;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MoneyChangingRequest {

    @Getter
    private final String moneyChangingRequestId;

    @Getter
    private final String targetMembershipId;

    @Getter
    private final MoneyChangingTypes changingType;

    @Getter
    private final MoneyChangingRequestStatusTypes changingMoneyStatus;

    @Getter
    private final Integer changingMoneyAmount;

    @Getter
    private final String uuid;

    @Getter
    private final LocalDate createdAt;

    public static MoneyChangingRequest create(
            MoneyChangingRequestId moneyChangingRequestId,
            TargetMembershipId targetMembershipId,
            ChangingType changingType,
            ChangingMoneyStatus changingMoneyStatus,
            ChangingMoneyAmount changingMoneyAmount,
            String uuid
    ) {
        return new MoneyChangingRequest(
                moneyChangingRequestId.value,
                targetMembershipId.value,
                changingType.value,
                changingMoneyStatus.value,
                changingMoneyAmount.value,
                uuid,
                LocalDate.now()
        );
    }


    public record MoneyChangingRequestId(String value){}
    public record TargetMembershipId(String value){}
    public record ChangingMoneyAmount(Integer value){}
    public record ChangingType(MoneyChangingTypes value){
        public ChangingType(int value){
            this(MoneyChangingTypes.values()[value]);
        }
    }
    public record ChangingMoneyStatus(MoneyChangingRequestStatusTypes value){
        public ChangingMoneyStatus(int value){
            this(MoneyChangingRequestStatusTypes.values()[value]);
        }
    }


}
