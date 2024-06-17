package ms2709.payservice.payment.adapter.`in`.web

import ms2709.payservice.payment.application.port.`in`.FinishSettlementCommand

/**
 *
 * 클래스 설명
 *
 * @class FinishSettlementRequest
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-17 오전 9:38
 */
class FinishSettlementRequest {
    var paymentId:String? = null

    fun of(): FinishSettlementCommand {
        return FinishSettlementCommand(paymentId)
    }
}