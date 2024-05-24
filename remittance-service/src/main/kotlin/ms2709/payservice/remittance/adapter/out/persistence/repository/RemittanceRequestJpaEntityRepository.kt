package ms2709.payservice.remittance.adapter.out.persistence.repository

import ms2709.payservice.remittance.adapter.out.persistence.entity.RemittanceRequestJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

/**
 *
 * 클래스 설명
 *
 * @class RemittanceRequestJpaEntityRepository
 * @author 심문섭
 * @version 1.0
 * @since 2024-05-25 오전 7:33
 */
interface RemittanceRequestJpaEntityRepository :JpaRepository<RemittanceRequestJpaEntity, Long>{
    @Query("select t1 from RemittanceRequestJpaEntity t1 where t1.fromMembershipId = :fromMembershipId")
    fun findByFromMembershipId(fromMembershipId: String): List<RemittanceRequestJpaEntity>
}