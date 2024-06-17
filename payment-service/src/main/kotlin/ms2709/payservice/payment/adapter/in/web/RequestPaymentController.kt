package ms2709.payservice.payment.adapter.`in`.web

import ms2709.global.WebAdapter
import ms2709.payservice.payment.application.port.`in`.RequestPaymentCommand
import ms2709.payservice.payment.application.port.`in`.RequestPaymentUseCase
import ms2709.payservice.payment.application.port.`in`.StatusListUseCase
import ms2709.payservice.payment.domain.Payment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 *
 * 클래스 설명
 *
 *  RequestPaymentController
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-02 오후 11:14
 */
@WebAdapter
@RestController
@RequestMapping("/payment")
class RequestPaymentController(
    private val requestPaymentUseCase: RequestPaymentUseCase,
    private val statusListUseCase: StatusListUseCase

) {

    @PostMapping("/request")
    fun requestPayment(paymentRequest: PaymentRequest):Payment {
        return requestPaymentUseCase.requestPayment(RequestPaymentCommand(
            paymentRequest.requestMemberId!!,
            paymentRequest.requestPrice!!,
            paymentRequest.franchiseId!!,
            paymentRequest.franchiseFeeRate!!
        ))
    }

    /**
     * 정산에서 사용하기 위한 정상 상태의 결제 목록을 조회한다.
     */
    @GetMapping("/normal-status")
    fun getNormalStatusPaymentList(normalStatusPaymentListRequest: NormalStatusPaymentListRequest):List<Payment>{
        return statusListUseCase.getNormalStatusPaymentList(normalStatusPaymentListRequest.of())
    }

    @PostMapping("/finish-settlement")
    fun finishPayment(finishSettlementRequest: FinishSettlementRequest) {
        return requestPaymentUseCase.finishSettlement(finishSettlementRequest.of())
    }
}