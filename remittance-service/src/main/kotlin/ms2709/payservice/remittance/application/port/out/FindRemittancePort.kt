package ms2709.payservice.remittance.application.port.out

import ms2709.payservice.remittance.adapter.out.persistence.entity.RemittanceRequestJpaEntity
import ms2709.payservice.remittance.application.port.`in`.FindRemittanceCommand


/**
 *
 * 클래스 설명
 *
 *  FindRemittancePort
 * @author 심문섭
 * @version 1.0
 * @since 2024-05-25 오전 7:42
 */
interface FindRemittancePort {
    fun findRemittanceHistory(command: FindRemittanceCommand): List<RemittanceRequestJpaEntity>
}