package ms2709.payservice.payment.adapter.`in`.web

import ms2709.payservice.payment.application.port.`in`.NormalStatusPaymentListCommand
import java.time.LocalDate

/**
 *
 * 클래스 설명
 *
 * @class NormalStatusPaymentListRequest
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-17 오전 8:46
 */
class NormalStatusPaymentListRequest {
    var startDate: LocalDate? = null
    var endDate: LocalDate? = null

    fun of():NormalStatusPaymentListCommand{
        return NormalStatusPaymentListCommand(startDate!!, endDate!!.plusDays(1))
    }
}