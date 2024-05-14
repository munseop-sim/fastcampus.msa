package com.ms2709.banking.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class RegisteredBankAccount
 * @since 2024-05-13 오후 10:03
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RegisteredBankAccount {
    @Getter
    private final String registeredBankAccountId;

    @Getter
    private final String membershipId;

    @Getter
    private final String bankName;

    @Getter
    private final String bankAccountNumber;

    @Getter
    private final boolean linkedStatusIsValid;

    public static RegisteredBankAccount create(
            RegisteredBankAccountId registeredBankAccountId,
            MembershipId membershipId,
            BankName bankName,
            BankAccountNumber bankAccountNumber,
            LinkedStatusIsValid linkedStatusIsValid
    ) {
        return new RegisteredBankAccount(
                registeredBankAccountId.value,
                membershipId.value,
                bankName.value,
                bankAccountNumber.value,
                linkedStatusIsValid.value
        );
    }


    public record RegisteredBankAccountId(String value){}
    public record MembershipId(String value){}
    public record BankName(String value){}
    public record BankAccountNumber(String value){}
    public record LinkedStatusIsValid(boolean value){}



}
