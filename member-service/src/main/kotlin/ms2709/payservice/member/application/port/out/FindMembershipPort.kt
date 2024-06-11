package ms2709.payservice.member.application.port.out

import ms2709.payservice.member.adapter.out.persistence.entity.MembershipJpaEntity
import ms2709.payservice.member.domain.Membership

/**
 *
 * 멤버십 조회를 위한 포트
 *
 * @class FindMembershipPort
 * @author 심문섭
 * @version 1.0
 * @since 2024-04-29 10:44 PM
 */
interface FindMembershipPort {
    fun findMembership(membershipId: Membership.MembershipId): MembershipJpaEntity?
    fun findMembershipByAddress(addressName: String): List<MembershipJpaEntity>
}