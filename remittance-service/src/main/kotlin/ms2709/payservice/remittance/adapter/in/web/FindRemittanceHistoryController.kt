package ms2709.payservice.remittance.adapter.`in`.web

import ms2709.global.WebAdapter
import ms2709.payservice.remittance.application.port.`in`.FindRemittanceCommand
import ms2709.payservice.remittance.application.port.`in`.FindRemittanceUseCase
import ms2709.payservice.remittance.domain.RemittanceRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


/**
 *
 * 클래스 설명
 *
 *  FindRemittanceHistoryController
 * @author 심문섭
 * @version 1.0
 * @since 2024-05-25 오전 8:05
 */
@WebAdapter
@RestController
@RequestMapping("/remittance")
class FindRemittanceHistoryController(
    private val findRemittanceUseCase: FindRemittanceUseCase
) {
    @GetMapping("/{membershipId}")
    fun findRemittanceHistory(@PathVariable membershipId: String?): List<RemittanceRequest> {
        val command: FindRemittanceCommand = FindRemittanceCommand(
            membershipId = membershipId ?: throw IllegalArgumentException("membershipId is required")
        )
        return findRemittanceUseCase.finRemittanceHistory(command)
    }

}