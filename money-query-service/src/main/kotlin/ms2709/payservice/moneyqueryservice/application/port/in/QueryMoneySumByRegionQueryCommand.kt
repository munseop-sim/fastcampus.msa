package ms2709.payservice.moneyqueryservice.application.port.`in`

import jakarta.validation.constraints.NotBlank
import ms2709.global.SelfValidating

/**
 *
 * 클래스 설명
 *
 * @class QueryMoneySumByRegionQueryCommand
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-12 오전 8:31
 */
class QueryMoneySumByRegionQueryCommand(
    @NotBlank private val address:String
):SelfValidating<QueryMoneySumByRegionQueryCommand>() {
    init {
        validateSelf()
    }

    fun getAddress():String {
        return address
    }
}