package ms2709.payservice.money.adapter.out.persistence.repository;

import ms2709.payservice.money.adapter.out.persistence.entity.MoneyChangingRequestJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoneyChangingRequestJpEntityRepository extends JpaRepository<MoneyChangingRequestJpaEntity, Long> {
}
