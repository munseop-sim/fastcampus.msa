package ms2709.payservice.remittance.application.port.out.money

/**
 *
 * 클래스 설명
 *
 * @class MoneyPort
 * @author 심문섭
 * @version 1.0
 * @since 2024-05-25 오전 7:47
 */
interface MoneyPort {

    fun getMoneyInfo(membershipId: String): MoneyInfo
    fun requestMoneyRecharging(membershipId: String, amount: Int): Boolean
    fun requestMoneyIncrease(membershipId: String, amount: Int): Boolean
    fun requestMoneyDecrease(membershipId: String, amount: Int): Boolean

}