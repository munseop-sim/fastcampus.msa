package ms2709.payservice.payment.adapter.out.persistence.entity

import jakarta.persistence.*
import java.time.LocalDateTime

/**
 *
 * 클래스 설명
 *
 * @class PaymentJpaEntity
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-02 오후 11:32
 */
@Entity
@Table(name="payment_request")
class PaymentJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var paymentId:Long? = null

    var requestMemberId:String? = null
    var requestPrice:Int? = null
    var franchiseId:String? = null
    var franchiseFeeRate:String? = null
    var paymentStatus:Int? = null //0:승인, 1:실패, 2:정산완료
    var approvedAt:LocalDateTime? = null

    constructor(
        requestMemberId: String,
        requestPrice: Int,
        franchiseId: String,
        franchiseFeeRate: String,
        paymentStatus: Int,
        approvedAt: LocalDateTime
    ) {
        this.requestMemberId = requestMemberId
        this.requestPrice = requestPrice
        this.franchiseId = franchiseId
        this.franchiseFeeRate = franchiseFeeRate
        this.paymentStatus = paymentStatus
        this.approvedAt = approvedAt
    }

    override fun toString(): String {
        return "PaymentJpaEntity(paymentId=$paymentId, requestMemberId=$requestMemberId, requestPrice=$requestPrice, franchiseId=$franchiseId, franchiseFeeRate=$franchiseFeeRate, paymentStatus=$paymentStatus, approvedAt=$approvedAt)"
    }


}