package ms2709.payservice.payment.application.port.out

import ms2709.payservice.payment.domain.Payment
import java.time.LocalDate

/**
 *
 * 클래스 설명
 *
 * @class GetPaymentStatusListPort
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-17 오전 8:51
 */
interface GetPaymentStatusListPort {
    fun getNormalStatusPaymentList(startDate: LocalDate, endDate: LocalDate): List<Payment>
}