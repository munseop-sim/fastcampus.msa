package ms2709.payservice.moneyqueryservice.application.port.out

/**
 *
 * 클래스 설명
 *
 * @class GetMoneySumByAddressPort
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-12 오전 10:20
 */
interface GetMoneySumByAddressPort {
    fun getMoneySumByAddress(address: String): Int
}