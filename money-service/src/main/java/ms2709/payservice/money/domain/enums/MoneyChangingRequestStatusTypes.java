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
}
