package ms2709.payservice.member.application.port.`in`

import ms2709.payservice.member.domain.Membership

interface FindMembershipUseCase {
    fun findMembership(command: FindMembershipCommand): Membership
}
