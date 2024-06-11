package ms2709.payservice.moneyaggregationservice.adapter.`in`.web

import ms2709.global.WebAdapter
import ms2709.payservice.moneyaggregationservice.application.port.`in`.GetMoneySumByAddressCommand
import ms2709.payservice.moneyaggregationservice.application.port.`in`.GetMoneySumByAddressUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 *
 * 클래스 설명
 *
 * @class GetMoneySumController
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-11 오후 12:46
 */
@WebAdapter
@RestController
@RequestMapping("/money/aggregation")
class GetMoneySumController(
    private val getMoneySumByAddressUseCase: GetMoneySumByAddressUseCase
) {
    @PostMapping("/get-money-sum-by-address")
    fun getMoneySumByAddress(@RequestBody request:GetMoneySumByAddressRequest):Int{
        return GetMoneySumByAddressCommand(request.address!!).run {
            getMoneySumByAddressUseCase.getMoneySumByAddress(this)
        }

    }
}