package ms2709.payservice.payment.application.port.`in`

/**
 *
 * 클래스 설명
 *
 *  RequestPaymentCommand
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-02 오후 11:22
 */
data class RequestPaymentCommand (
    val requestMemberId:String,
    val requestPrice:String,
    val franchiseId:String,
    val franchiseFeeRate:String
)