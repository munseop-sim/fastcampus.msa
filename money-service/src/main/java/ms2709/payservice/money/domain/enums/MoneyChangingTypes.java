package ms2709.payservice.money.domain.enums;

import lombok.Getter;

public enum MoneyChangingTypes {
    INCREASE(1),
    DECREASE(2);

    @Getter
    private final Integer changingTypeIndex;
    MoneyChangingTypes(int changingTypeIndex) {
        this.changingTypeIndex = changingTypeIndex;
    }
}
