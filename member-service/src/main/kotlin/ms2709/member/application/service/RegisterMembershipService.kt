package ms2709.member.application.service

import ms2709.global.UseCase
import ms2709.member.adapter.out.persistence.MembershipMapper
import ms2709.member.application.port.`in`.RegisterMembershipCommand
import ms2709.member.application.port.`in`.RegisterMembershipUseCase
import ms2709.member.application.port.out.RegisterMembershipPort
import ms2709.member.domain.Membership

import org.springframework.transaction.annotation.Transactional

/**
 * @class RegisterMembershipService
 * @author 심문섭
 * @version 1.0
 * @since 2024-04-29 10:16 PM
 */
@UseCase
@Transactional
class RegisterMembershipService(
    private val registerMembershipPort: RegisterMembershipPort,
    private val membershipMapper: MembershipMapper
): RegisterMembershipUseCase {
    override fun registerMembership(command: RegisterMembershipCommand): Membership {
        val jpaEntity = registerMembershipPort.createMembership(
            Membership.MemberName(command.name),
            Membership.MemberEmail(command.email),
            Membership.MemberAddress(command.address),
            Membership.MemberIsValid(command.isValid),
            Membership.MemberIsCorp(command.isCorp)
        )

        return membershipMapper.mapToDomainEntity(jpaEntity)
    }
}