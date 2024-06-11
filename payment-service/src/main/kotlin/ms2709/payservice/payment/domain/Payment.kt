package ms2709.payservice.payment.domain

import java.time.LocalDateTime

/**
 *
 * 클래스 설명
 *
 *  Payment
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-02 오후 11:15
 */
class Payment {
    val paymentId:Long
    val requestMembershipId:String
    val requestPrice:Int
    val franchiseId:String
    val franchiseFeeRate:String
    val paymentStatus:Int
    val paymentTime:LocalDateTime

    private constructor(paymentId: Long, requestMembershipId: String, requestPrice: Int, franchiseId: String, franchiseFeeRate: String, paymentStatus: Int, paymentTime: LocalDateTime) {
        this.paymentId = paymentId
        this.requestMembershipId = requestMembershipId
        this.requestPrice = requestPrice
        this.franchiseId = franchiseId
        this.franchiseFeeRate = franchiseFeeRate
        this.paymentStatus = paymentStatus
        this.paymentTime = paymentTime
    }

    companion object {
        fun create(paymentId: PaymentId,
                   requestMembershipId: RequestMembershipId,
                   requestPrice: RequestPrice,
                   franchiseId: FranchiseId,
                   franchiseFeeRate: FranchiseFeeRate,
                   paymentStatus: PaymentStatus,
                   paymentTime: PaymentTime): Payment {
            return Payment(paymentId.paymentId,
                requestMembershipId.requestMembershipId,
                requestPrice.requestPrice,
                franchiseId.franchiseId,
                franchiseFeeRate.franchiseFeeRate,
                paymentStatus.paymentStatus,
                paymentTime.paymentTime)
        }
    }


    data class PaymentId(val paymentId:Long)
    data class RequestMembershipId(val requestMembershipId:String)
    data class RequestPrice(val requestPrice:Int)
    data class FranchiseId(val franchiseId:String)
    data class FranchiseFeeRate(val franchiseFeeRate:String)
    data class PaymentStatus(val paymentStatus:Int)
    data class PaymentTime(val paymentTime:LocalDateTime)

}