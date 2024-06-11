package ms2709.payservice.member.adapter.`in`.web

import ms2709.global.WebAdapter
import ms2709.payservice.member.application.port.`in`.FindMembershipCommand
import ms2709.payservice.member.application.port.`in`.FindMembershipListByAddressCommand
import ms2709.payservice.member.application.port.`in`.FindMembershipUseCase
import ms2709.payservice.member.domain.Membership
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 *
 * 멤버십 조회를 위한 컨트롤러
 *
 * @class FindMembershipController
 * @author 심문섭
 * @version 1.0
 * @since 2024-04-29 10:39 PM
 */
@WebAdapter
@RestController
@RequestMapping("/membership")
class FindMembershipController(
    private val findMembershipUseCase: FindMembershipUseCase
) {
    @GetMapping("/find/{membershipId}")
    fun findMembership(@PathVariable membershipId: String):Membership {
        return FindMembershipCommand(membershipId).run {
            findMembershipUseCase.findMembership(this)
        }
    }
    @GetMapping("/find/address/{addressName}")
    fun findMembershipByAddress(@PathVariable addressName: String):List<Membership> {
        return FindMembershipListByAddressCommand(addressName).run {
            findMembershipUseCase.findMembershipByAddress(this)
        }
    }
}