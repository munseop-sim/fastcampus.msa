package ms2709.payservice.moneyaggregationservice.application.port.out

/**
 *
 * 클래스 설명
 *
 * @class GetMoneySumPort
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-11 오후 12:55
 */
interface GetMoneySumPort {
    fun getMoneySumByMembershipIdList(membershipIdList: List<String>): List<MemberMoney>
}