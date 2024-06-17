package ms2709.payservice.payment.application.port.`in`

import jakarta.validation.constraints.NotNull
import ms2709.global.SelfValidating
import java.time.LocalDate

/**
 *
 * 클래스 설명
 *
 * @class NormalStatusPaymentListCommand
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-17 오전 8:47
 */
class NormalStatusPaymentListCommand (
    @field:NotNull(message = "시작일은 필수입니다.") val startDate: LocalDate,
    @field:NotNull(message = "종료일은 필수입니다.") val endDate: LocalDate
):SelfValidating<NormalStatusPaymentListCommand>() {
    init {
        this.validateSelf()
    }
}