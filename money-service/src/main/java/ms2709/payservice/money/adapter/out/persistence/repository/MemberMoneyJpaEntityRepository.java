package ms2709.payservice.money.adapter.out.persistence.repository;

import ms2709.payservice.money.adapter.out.persistence.entity.MemberMoneyJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberMoneyJpaEntityRepository extends JpaRepository<MemberMoneyJpaEntity, Long>{
}
