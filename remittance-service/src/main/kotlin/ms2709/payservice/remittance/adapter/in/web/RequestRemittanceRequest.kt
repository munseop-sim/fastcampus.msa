package ms2709.payservice.remittance.adapter.`in`.web

/**
 *
 * 송금 요청을 위한 정보가 담긴 class
 *
 *  RequestRemittanceRequest
 * @author 심문섭
 * @version 1.0
 * @since 2024-05-25 오전 6:42
 */
data class RequestRemittanceRequest (
    val fromMembershipId:String,
    val toMembershipId:String,
    val toBankName:String,
    val toBankAccountNumber:String,
    val remittanceType:Int, //0: membership(내부고객), 1: bank(외부은행계좌)
    val amount:Int //송금요청 금액
)