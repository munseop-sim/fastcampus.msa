package ms2709.payservice.payment.adapter.out.persistence

import ms2709.global.Mapper
import ms2709.payservice.payment.adapter.out.persistence.entity.PaymentJpaEntity
import ms2709.payservice.payment.domain.Payment

/**
 *
 * 클래스 설명
 *
 * @class PaymentMapper
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-02 오후 11:36
 */
@Mapper
class PaymentMapper {
    fun mapToDomainEntity(paymentJpaEntity: PaymentJpaEntity): Payment {
        return Payment.create(
            paymentJpaEntity.paymentId?.let {
                Payment.PaymentId(it)
            } ?: throw RuntimeException("paymentId is null"),
paymentJpaEntity.requestMemberId?.let {
                Payment.RequestMembershipId(it.toString())
            } ?: throw RuntimeException("requestMemberId is null"),
paymentJpaEntity.requestPrice?.let {
                Payment.RequestPrice(it)
            } ?: throw RuntimeException("requestPrice is null"),
paymentJpaEntity.franchiseId?.let {
                Payment.FranchiseId(it.toString())
            } ?: throw RuntimeException("franchiseId is null"),
paymentJpaEntity.franchiseFeeRate?.let {
                Payment.FranchiseFeeRate(it)
            } ?: throw RuntimeException("franchiseFeeRate is null"),
paymentJpaEntity.paymentStatus?.let {
                Payment.PaymentStatus(it)
            } ?: throw RuntimeException("paymentStatus is null"),
paymentJpaEntity.approvedAt?.let {
                Payment.PaymentTime(it)
            } ?: throw RuntimeException("approvedAt is null")
            )
    }
}