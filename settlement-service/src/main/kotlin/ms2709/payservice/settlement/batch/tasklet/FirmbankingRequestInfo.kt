package ms2709.payservice.settlement.batch.tasklet

/**
 *
 * 클래스 설명
 *
 * @class FirmbankingRequestInfo
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-18 오후 10:11
 */
class FirmbankingRequestInfo (
    val bankName:String,
    val bankAccountNumber:String
){
    var moneyAmount:Int = 0
}