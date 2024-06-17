package ms2709.payservice.payment.application.port.`in`

import ms2709.global.SelfValidating

/**
 *
 * 클래스 설명
 *
 * @class FinishSettlementCommand
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-17 오전 9:39
 */
class FinishSettlementCommand(
    initPaymentId:String?
):SelfValidating<FinishSettlementCommand>() {
    init {
        this.validateSelf()
    }

    val paymentId:String = initPaymentId!!


}