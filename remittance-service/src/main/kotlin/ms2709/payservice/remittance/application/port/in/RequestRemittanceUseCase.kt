package ms2709.payservice.remittance.application.port.`in`

import ms2709.payservice.remittance.domain.RemittanceRequest

interface RequestRemittanceUseCase {
    fun requestRemittance(request: RequestRemittanceCommand): RemittanceRequest
}
