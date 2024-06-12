package ms2709.payservice.moneyqueryservice.application.port.`in`

import ms2709.payservice.moneyqueryservice.domain.MoneySumByRegion

/**
 *
 * 클래스 설명
 *
 * @class QueryMoneySumByRegionUseCase
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-12 오전 8:30
 */
interface QueryMoneySumByRegionUseCase {
    fun queryMoneySumByRegion(command: QueryMoneySumByRegionQueryCommand): MoneySumByRegion
}