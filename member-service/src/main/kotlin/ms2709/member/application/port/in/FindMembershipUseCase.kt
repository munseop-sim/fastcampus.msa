package ms2709.member.application.port.`in`

import ms2709.member.domain.Membership

interface FindMembershipUseCase {
    fun findMembership(command: FindMembershipCommand): Membership
}
