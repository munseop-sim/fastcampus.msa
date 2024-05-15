package com.ms2709.banking.adapter.out.persistence;

import com.ms2709.banking.adapter.out.persistence.entity.RegisteredBankAccountJpaEntity;
import com.ms2709.banking.domain.RegisteredBankAccount;
import ms2709.global.Mapper;
import org.springframework.stereotype.Component;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class RegisteredBankAccountMapper
 * @since 2024-05-13 오후 10:15
 */
@Mapper
public class RegisteredBankAccountMapper {
    public RegisteredBankAccount mapToDomainEntity(RegisteredBankAccountJpaEntity jpaEntity) {
        return RegisteredBankAccount.create(
                new RegisteredBankAccount.RegisteredBankAccountId(jpaEntity.getRegisteredBankAccountId()+""),
                new RegisteredBankAccount.MembershipId(jpaEntity.getMembershipId()+""),
                new RegisteredBankAccount.BankName(jpaEntity.getBankName()),
                new RegisteredBankAccount.BankAccountNumber(jpaEntity.getBankAccountNumber()),
                new RegisteredBankAccount.LinkedStatusIsValid(jpaEntity.isLinkedStatusIsValid())
        );
    }
}
