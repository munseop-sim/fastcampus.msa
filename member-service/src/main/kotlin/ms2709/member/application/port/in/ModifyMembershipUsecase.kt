package ms2709.member.application.port.`in`

import ms2709.member.domain.Membership

/**
 *
 * 클래스 설명
 *
 * @class ModifyMembershipUsecase
 * @author 심문섭
 * @version 1.0
 * @since 2024-05-08 오전 8:19
 */
interface ModifyMembershipUsecase {
    fun modifyMembership(command: ModifyMembershipCommand): Membership
}