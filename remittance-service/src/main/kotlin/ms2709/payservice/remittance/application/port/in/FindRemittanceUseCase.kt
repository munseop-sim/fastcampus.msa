package ms2709.payservice.remittance.application.port.`in`

import ms2709.payservice.remittance.domain.RemittanceRequest

/**
 *
 * 클래스 설명
 *
 * @class FindRemittanceUseCase
 * @author 심문섭
 * @version 1.0
 * @since 2024-05-25 오전 7:38
 */
interface FindRemittanceUseCase {
    fun finRemittanceHistory(command: FindRemittanceCommand): List<RemittanceRequest>
}