package ms2709.payservice.payment.application.port.out

import ms2709.payservice.payment.domain.Payment

/**
 *
 * 클래스 설명
 *
 *  CreatePaymentPort
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-02 오후 11:25
 */
interface CreatePaymentPort {
    fun createPayment(requestMemberId:String, requestPrice:String, franchiseId:String, franchiseFeeRate:String): Payment
}