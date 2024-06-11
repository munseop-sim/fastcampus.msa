package ms2709.payservice.member.adapter.`in`.web

import ms2709.global.WebAdapter
import ms2709.payservice.member.application.port.`in`.ModifyMembershipCommand
import ms2709.payservice.member.application.port.`in`.ModifyMembershipUsecase
import ms2709.payservice.member.domain.Membership
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 *
 * 클래스 설명
 *
 *  ModifyMembershipController
 * @author 심문섭
 * @version 1.0
 * @since 2024-05-08 오전 8:18
 */
@WebAdapter
@RestController
@RequestMapping("/membership")
class ModifyMembershipController(
    private val modifyMembershipUseCase: ModifyMembershipUsecase
) {

    @PostMapping("/modify")
    fun modifyMembership(@RequestBody modifyMembershipRequest: ModifyMembershipRequest):Membership {
        return ModifyMembershipCommand(
            membershipId = modifyMembershipRequest.membershipId,
            name = modifyMembershipRequest.name,
            address = modifyMembershipRequest.address,
            email = modifyMembershipRequest.email,
            isValid = modifyMembershipRequest.isValid,
            isCorp = modifyMembershipRequest.isCorp
        ).let {
            modifyMembershipUseCase.modifyMembership(it)
        }
    }
}