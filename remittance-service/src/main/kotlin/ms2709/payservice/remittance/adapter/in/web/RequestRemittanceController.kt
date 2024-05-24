package ms2709.payservice.remittance.adapter.`in`.web

import ms2709.global.WebAdapter
import ms2709.payservice.remittance.application.port.`in`.RequestRemittanceCommand
import ms2709.payservice.remittance.application.port.`in`.RequestRemittanceUseCase
import ms2709.payservice.remittance.domain.RemittanceRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 *
 * 클래스 설명
 *
 * @class RequestRemittanceController
 * @author 심문섭
 * @version 1.0
 * @since 2024-05-25 오전 6:43
 */
@WebAdapter
@RestController
@RequestMapping("/remittance")
class RequestRemittanceController(
    private val requestRemittanceUseCase: RequestRemittanceUseCase
) {
    @PostMapping("/request")
    fun requestRemittance(@RequestBody request:RequestRemittanceRequest):RemittanceRequest {
        val command = RequestRemittanceCommand(
            request.fromMembershipId,
            request.toMembershipId,
            request.toBankName,
            request.toBankAccountNumber,
            request.remittanceType,
            request.amount
        )
        return requestRemittanceUseCase.requestRemittance(command)
    }
}