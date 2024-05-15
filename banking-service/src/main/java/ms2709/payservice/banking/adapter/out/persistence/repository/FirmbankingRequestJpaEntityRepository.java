package ms2709.payservice.banking.adapter.out.persistence.repository;

import ms2709.payservice.banking.adapter.out.persistence.entity.FirmbankingRequestJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class RequestFirmbankingJpaEntityRepository
 * @since 2024-05-15 오전 11:39
 */
public interface FirmbankingRequestJpaEntityRepository extends JpaRepository<FirmbankingRequestJpaEntity,Long> {
}
