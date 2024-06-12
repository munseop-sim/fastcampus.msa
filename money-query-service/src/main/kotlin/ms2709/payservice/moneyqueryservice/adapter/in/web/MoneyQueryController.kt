package ms2709.payservice.moneyqueryservice.adapter.`in`.web

import ms2709.global.WebAdapter
import ms2709.payservice.moneyqueryservice.application.port.`in`.QueryMoneySumByRegionQueryCommand
import ms2709.payservice.moneyqueryservice.application.port.`in`.QueryMoneySumByRegionUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 *
 * 클래스 설명
 *
 * @class MoneyQueryController
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-12 오전 8:26
 */
@WebAdapter
@RestController
@RequestMapping("/money/query")
class MoneyQueryController (
    private val queryMoneySumByRegionUseCase: QueryMoneySumByRegionUseCase
){
    @GetMapping("get-money-sum-by-address/{address}")
    fun getMoneySumByAddress(@PathVariable address: String): Int {
        return queryMoneySumByRegionUseCase
            .queryMoneySumByRegion(
                QueryMoneySumByRegionQueryCommand(address)
            ).getMoneySum().toInt()
    }
}