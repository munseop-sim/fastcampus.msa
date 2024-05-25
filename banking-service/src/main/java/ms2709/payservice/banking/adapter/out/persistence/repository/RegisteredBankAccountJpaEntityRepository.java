package ms2709.payservice.banking.adapter.out.persistence.repository;

import ms2709.payservice.banking.adapter.out.persistence.entity.RegisteredBankAccountJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 *  RegisteredBankAccountJpaEntity 를 위한 JPA Repository
 *
 * @author 심문섭
 * @version 1.0
 * @class RegisteredBankAccountJpaEntityRepository
 * @since 2024-05-13 오후 10:16
 */
public interface RegisteredBankAccountJpaEntityRepository extends JpaRepository<RegisteredBankAccountJpaEntity, Long> {
    @Query("SELECT e  FROM RegisteredBankAccountJpaEntity e WHERE e.membershipId = :membershipId")
    List<RegisteredBankAccountJpaEntity> findByMembershipId(@Param("membershipId") String membershipId);
}
