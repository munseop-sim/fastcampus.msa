package ms2709.member.application.service

import ms2709.member.adapter.out.persistence.MembershipMapper
import ms2709.member.application.port.`in`.FindMembershipCommand
import ms2709.member.application.port.`in`.FindMembershipUseCase
import ms2709.member.application.port.out.FindMembershipPort
import ms2709.member.domain.Membership
import ms2709.member.global.UseCase
import org.springframework.transaction.annotation.Transactional

/**
 * @class FindMembershipService
 * @author 심문섭
 * @version 1.0
 * @since 2024-04-29 10:43 PM
 */
@UseCase
@Transactional(readOnly = true)
class FindMembershipService(
private val findMembershipPort: FindMembershipPort,
private val membershipMapper: MembershipMapper
): FindMembershipUseCase {
    override fun findMembership(command: FindMembershipCommand): Membership {
        return findMembershipPort.findMembership(Membership.MembershipId(command.membershipId))
            ?.let { membershipMapper.mapToDomainEntity(it) }
            ?: throw IllegalArgumentException("Membership not found")
    }
}