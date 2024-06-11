package ms2709.payservice.moneyaggregationservice.application.port.`in`

/**
 *
 * 클래스 설명
 *
 * @class GetMoneySumByAddressUseCase
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-11 오후 12:52
 */
interface GetMoneySumByAddressUseCase {
    fun getMoneySumByAddress(command: GetMoneySumByAddressCommand): Int
}