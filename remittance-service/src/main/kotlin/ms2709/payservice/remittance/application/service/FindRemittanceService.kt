package ms2709.payservice.remittance.application.service


import ms2709.global.UseCase
import ms2709.payservice.remittance.adapter.out.persistence.RemittanceRequestMapper
import ms2709.payservice.remittance.application.port.`in`.FindRemittanceCommand
import ms2709.payservice.remittance.application.port.`in`.FindRemittanceUseCase
import ms2709.payservice.remittance.application.port.out.FindRemittancePort
import ms2709.payservice.remittance.domain.RemittanceRequest
import org.springframework.transaction.annotation.Transactional

/**
 *
 * 클래스 설명
 *
 * @class FindRemittanceService
 * @author 심문섭
 * @version 1.0
 * @since 2024-05-25 오전 8:02
 */
@UseCase
@Transactional(readOnly = true)
class FindRemittanceService(
    private val findRemittancePort: FindRemittancePort,
    private val mapper: RemittanceRequestMapper
) : FindRemittanceUseCase {
    override fun finRemittanceHistory(command: FindRemittanceCommand): List<RemittanceRequest> {
        return findRemittancePort.findRemittanceHistory(command).map {
            mapper.mapToEntity(it)
        }
    }
}