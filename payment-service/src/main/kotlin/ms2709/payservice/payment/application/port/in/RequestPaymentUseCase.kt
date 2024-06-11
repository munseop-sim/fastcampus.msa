package ms2709.payservice.payment.application.port.`in`

import ms2709.payservice.payment.domain.Payment

/**
 *
 * 클래스 설명
 *
 *  RequestPaymentUseCase
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-02 오후 11:22
 */
interface RequestPaymentUseCase {
    fun requestPayment(requestPaymentCommand: RequestPaymentCommand):Payment
}