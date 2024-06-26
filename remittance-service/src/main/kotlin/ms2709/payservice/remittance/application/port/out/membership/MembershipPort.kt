package ms2709.payservice.remittance.application.port.out.membership

/**
 *
 * 클래스 설명
 *
 *  MembershipPort
 * @author 심문섭
 * @version 1.0
 * @since 2024-05-25 오전 6:51
 */
interface MembershipPort {
    fun getMembershipStatus(membershipId: String): MembershipStatus
}