package com.ms2709.banking.adapter.out.persistence.repository;

import com.ms2709.banking.adapter.out.persistence.entity.RegisteredBankAccountJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *  RegisteredBankAccountJpaEntity 를 위한 JPA Repository
 *
 * @author 심문섭
 * @version 1.0
 * @class RegisteredBankAccountJpaEntityRepository
 * @since 2024-05-13 오후 10:16
 */
public interface RegisteredBankAccountJpaEntityRepository extends JpaRepository<RegisteredBankAccountJpaEntity, Long> {
}
