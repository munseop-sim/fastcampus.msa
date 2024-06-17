package ms2709.payservice.payment.adapter.out.persistence.repository

import ms2709.payservice.payment.adapter.out.persistence.entity.PaymentJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.LocalDate
import java.time.LocalDateTime

/**
 *
 * 클래스 설명
 *
 *  PaymentJpaEntityRepository
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-02 오후 11:35
 */
interface PaymentJpaEntityRepository :JpaRepository<PaymentJpaEntity, Long> {
    @Query("SELECT p FROM PaymentJpaEntity p WHERE p.approvedAt BETWEEN :startDate AND :endDate AND p.paymentStatus = :status")
    fun getPaymentListByTermAndStatus(
        @Param("startDate") startDate: LocalDateTime,
        @Param("endDate") endDate: LocalDateTime,
        @Param("status") status:Int
    ): List<PaymentJpaEntity>

}