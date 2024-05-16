package ms2709.payservice.money.adapter.out.persistence.repository;

import ms2709.payservice.money.adapter.out.persistence.entity.MemberMoneyJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberMoneyJpaEntityRepository extends JpaRepository<MemberMoneyJpaEntity, Long>{

    @Query("select m from MemberMoneyJpaEntity m where m.membershipId = :membershipId order by m.memberMoneyId desc limit 1")
    List<MemberMoneyJpaEntity> findByMembershipId(Long membershipId);
}
