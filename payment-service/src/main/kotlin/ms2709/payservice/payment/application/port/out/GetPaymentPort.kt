package ms2709.payservice.payment.application.port.out

import ms2709.payservice.payment.adapter.out.persistence.entity.PaymentJpaEntity

/**
 *
 * 클래스 설명
 *
 * @class GetPaymentPort
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-17 오전 9:44
 */
interface GetPaymentPort {
    fun getPayment(paymentId: String): PaymentJpaEntity?
}