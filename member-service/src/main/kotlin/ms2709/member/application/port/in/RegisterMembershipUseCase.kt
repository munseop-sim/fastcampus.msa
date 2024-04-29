package ms2709.member.application.port.`in`

import ms2709.member.domain.Membership

/**
 *
 * 클래스 설명
 *
 * @class RegisterMembershipUseCase
 * @author 심문섭
 * @version 1.0
 * @since 2024-04-29 10:15PM
 */
interface RegisterMembershipUseCase {
    fun registerMembership(command: RegisterMembershipCommand):Membership
}