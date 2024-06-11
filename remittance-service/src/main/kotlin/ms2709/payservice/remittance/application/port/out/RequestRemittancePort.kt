package ms2709.payservice.remittance.application.port.out

import ms2709.payservice.remittance.adapter.out.persistence.entity.RemittanceRequestJpaEntity
import ms2709.payservice.remittance.application.port.`in`.RequestRemittanceCommand



/**
 *
 * 클래스 설명
 *
 *  RequestRemittancePort
 * @author 심문섭
 * @version 1.0
 * @since 2024-05-25 오전 7:41
 */
interface RequestRemittancePort {
    fun createRemittanceRequestHistory(command: RequestRemittanceCommand): RemittanceRequestJpaEntity
    fun saveRemittanceRequestHistory(entity: RemittanceRequestJpaEntity): Boolean
}