package ms2709.payservice.money.domain.enums;

import lombok.Getter;

public enum MoneyChangingRequestStatusTypes {
    REQUESTED(0),
    SUCCESS(1),
    FAILED(2);

    @Getter
    private final Integer statusIndex;

    MoneyChangingRequestStatusTypes(Integer statusIndex) {
        this.statusIndex = statusIndex;
    }

    public static MoneyChangingRequestStatusTypes from(Integer statusIndex) {
        for (MoneyChangingRequestStatusTypes type : MoneyChangingRequestStatusTypes.values()) {
            if (type.statusIndex.equals(statusIndex)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown MoneyChangingRequestStatusType index: " + statusIndex);
    }
}
