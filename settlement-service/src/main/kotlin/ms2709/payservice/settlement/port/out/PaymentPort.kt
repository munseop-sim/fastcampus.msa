package ms2709.payservice.settlement.port.out

import ms2709.payservice.settlement.port.out.dto.Payment
import java.time.LocalDate

/**
 *
 * 클래스 설명
 *
 * @class PaymentPort
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-18 오후 10:14
 */
interface PaymentPort {
    fun getNormalStatusPayments(startDate:LocalDate, endDate: LocalDate): List<Payment>
    fun finishSettlement(paymentId:Long)
}