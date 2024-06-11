package ms2709.payservice.remittance.application.port.out.membership

/**
 *
 * 클래스 설명
 *
 *  MembershipStatus
 * @author 심문섭
 * @version 1.0
 * @since 2024-05-25 오전 6:54
 */
data class MembershipStatus(
    var membershipId:String,
    var isValid:Boolean,
)