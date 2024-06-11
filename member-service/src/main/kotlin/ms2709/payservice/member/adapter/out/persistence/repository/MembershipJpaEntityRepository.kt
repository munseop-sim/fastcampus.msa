package ms2709.payservice.member.adapter.out.persistence.repository

import ms2709.payservice.member.adapter.out.persistence.entity.MembershipJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

/**
 *
 * 멤버십 정보를 저장하고 조회하기 위한 JPA Repository
 *
 * @class MembershipJpaEntityRepository
 * @author 심문섭
 * @version 1.0
 * @since 2024-04-29 9:10PM
 */
interface MembershipJpaEntityRepository : JpaRepository<MembershipJpaEntity, Long>{
    @Query("select m from MembershipJpaEntity m where m.address = :address")
    fun findByAddress(address: String): List<MembershipJpaEntity>
}