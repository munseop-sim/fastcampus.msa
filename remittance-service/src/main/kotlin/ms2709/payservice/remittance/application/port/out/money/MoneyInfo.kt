package ms2709.payservice.remittance.application.port.out.money

/**
 *
 * 클래스 설명
 *
 *  MoneyInfo
 * @author 심문섭
 * @version 1.0
 * @since 2024-05-25 오전 7:46
 */
data class MoneyInfo (
    val membershipId:String,
    val balance:Int
)