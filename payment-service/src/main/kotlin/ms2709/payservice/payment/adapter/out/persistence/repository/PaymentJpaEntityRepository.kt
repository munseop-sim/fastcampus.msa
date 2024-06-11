package ms2709.payservice.payment.adapter.out.persistence.repository

import ms2709.payservice.payment.adapter.out.persistence.entity.PaymentJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

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
}