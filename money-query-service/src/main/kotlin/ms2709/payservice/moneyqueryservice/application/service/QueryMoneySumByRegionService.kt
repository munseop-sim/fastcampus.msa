package ms2709.payservice.moneyqueryservice.application.service

import ms2709.global.UseCase
import ms2709.payservice.moneyqueryservice.application.port.`in`.QueryMoneySumByRegionQueryCommand
import ms2709.payservice.moneyqueryservice.application.port.`in`.QueryMoneySumByRegionUseCase
import ms2709.payservice.moneyqueryservice.domain.MoneySumByRegion
import org.axonframework.queryhandling.QueryGateway
import org.slf4j.LoggerFactory

/**
 *
 * 클래스 설명
 *
 * @class QueryMoneySumByRegionService
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-12 오전 8:38
 */
@UseCase
class QueryMoneySumByRegionService(
    private val queryGateway: QueryGateway
): QueryMoneySumByRegionUseCase {
    private val log  = LoggerFactory.getLogger(QueryMoneySumByRegionService::class.java)
    override fun queryMoneySumByRegion(command: QueryMoneySumByRegionQueryCommand): MoneySumByRegion {
        log.info("QueryMoneySumByRegionService: $command")
        return queryGateway.query(command, MoneySumByRegion::class.java).get()
    }
}