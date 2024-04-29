package ms2709.member.adapter.`in`.web

import ms2709.member.application.port.`in`.RegisterMembershipCommand
import ms2709.member.application.port.`in`.RegisterMembershipUseCase
import ms2709.member.domain.Membership
import ms2709.member.global.WebAdapter
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

/**
 *
 * 멤버십 등록을 위한 컨트롤러
 *
 * @class RegisterMembershipController
 * @author 심문섭
 * @version 1.0
 * @since 2024-04-29 8:40AM
 */

@WebAdapter
@RequestMapping("/membership")
class RegisterMembershipController(
    private val registerMembershipUseCase: RegisterMembershipUseCase
){

    @PostMapping("/register")
    fun registerMembership(@RequestBody request: RegisterMembershipRequest): Membership {
        return RegisterMembershipCommand(
            name = request.name,
            email = request.email,
            address = request.address,
            isValid = true,
            isCorp = request.isCorp
        ).run {
            registerMembershipUseCase.registerMembership(this)
        }
    }
}
