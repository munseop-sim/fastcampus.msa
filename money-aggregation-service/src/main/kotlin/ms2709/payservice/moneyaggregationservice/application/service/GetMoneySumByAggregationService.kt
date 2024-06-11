package ms2709.payservice.moneyaggregationservice.application.service

import ms2709.global.UseCase
import ms2709.payservice.moneyaggregationservice.application.port.`in`.GetMoneySumByAddressCommand
import ms2709.payservice.moneyaggregationservice.application.port.`in`.GetMoneySumByAddressUseCase
import ms2709.payservice.moneyaggregationservice.application.port.out.GetMembershipPort
import ms2709.payservice.moneyaggregationservice.application.port.out.GetMoneySumPort

/**
 *
 * 클래스 설명
 *
 * @class GetMoneySumByAggregationService
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-11 오후 12:54
 */
@UseCase
class GetMoneySumByAggregationService(
    private val getMoneySumPort: GetMoneySumPort,
    private val getMembershipPort:GetMembershipPort
) : GetMoneySumByAddressUseCase {

    override fun getMoneySumByAddress(command: GetMoneySumByAddressCommand): Int {
        val address= command.getAddress()
        val membershipIdList = getMembershipPort.getMembershipByAddress(address)
        var sum = 0
        membershipIdList.chunked(100).forEach {
            sum += getMoneySumPort.getMoneySumByMembershipIdList(it).run{
                this.mapNotNull { response-> response.balance }
            }.sum()
        }
        return sum

    }
}