package ms2709.payservice.settlement.adapter.out.dto

/**
 *
 * 클래스 설명
 *
 * @class RequestFirmbankingRequest
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-18 오후 11:39
 */
class RequestFirmbankingRequest {
    var fromBankName:String? = null
    var fromBankAccountNumber:String? = null
    var toBankName:String? = null
    var toBankAccountNumber:String? = null
    var moneyAmount:Int? = null
}