package ms2709.payservice.payment.application.port.`in`

import ms2709.payservice.payment.domain.Payment

/**
 *
 * 클래스 설명
 *
 * @class StatusListUseCase
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-17 오전 8:49
 */
interface StatusListUseCase {
    fun getNormalStatusPaymentList(normalStatusPaymentListCommand: NormalStatusPaymentListCommand): List<Payment>
}