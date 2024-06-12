package ms2709.payservice.moneyqueryservice.application.port.out

/**
 *
 * 클래스 설명
 *
 * @class GetMemberAddressInfoPort
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-12 오전 10:52
 */
interface GetMemberAddressInfoPort {
    fun getMemberAddressInfo(membershipId: String): MemberAddressInfo?
}