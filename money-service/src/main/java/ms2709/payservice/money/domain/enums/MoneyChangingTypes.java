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

    public static MoneyChangingTypes from(int changingTypeIndex) {
        for (MoneyChangingTypes type : MoneyChangingTypes.values()) {
            if (type.changingTypeIndex.equals(changingTypeIndex)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown MoneyChangingType index: " + changingTypeIndex);
    }
}
