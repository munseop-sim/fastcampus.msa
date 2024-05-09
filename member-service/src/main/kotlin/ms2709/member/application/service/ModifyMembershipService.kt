package ms2709.member.application.service

import ms2709.global.UseCase
import ms2709.member.adapter.out.persistence.MembershipMapper
import ms2709.member.application.port.`in`.ModifyMembershipCommand
import ms2709.member.application.port.`in`.ModifyMembershipUsecase
import ms2709.member.application.port.out.ModifyMembershipPort
import ms2709.member.domain.Membership
import org.springframework.transaction.annotation.Transactional

/**
 *
 * 클래스 설명
 *
 * @class ModifyMembershipService
 * @author 심문섭
 * @version 1.0
 * @since 2024-05-08 오전 8:20
 */
@UseCase
@Transactional
class ModifyMembershipService(
    private val modifyMembershipPort: ModifyMembershipPort,
    private val membershipMapper: MembershipMapper
) : ModifyMembershipUsecase {
    override fun modifyMembership(command: ModifyMembershipCommand): Membership {
        val jpaEntity = modifyMembershipPort.modifyMembership(
            Membership.MembershipId(command.membershipId!!),
            Membership.MemberName(command.name!!),
            Membership.MemberEmail(command.email!!),
            Membership.MemberAddress(command.address!!),
            Membership.MemberIsValid(command.isValid!!),
            Membership.MemberIsCorp(command.isCorp!!)
        )

        return membershipMapper.mapToDomainEntity(jpaEntity)
    }
}
