package ms2709.payservice.settlement.port.out.dto

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.time.LocalDate

/**
 *
 * 클래스 설명
 *
 * @class Payment
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-18 오후 10:19
 */
class Payment {
    var paymentId:Long? = null
    var requestMembershipId:String? = null
    var requestPrice:Int? = null
    var franchiseId:String? = null
    var franchiseFeeRate:String? = null
    var paymentStatus:Int? = null
    var paymentTime: String? = null
    val approvedAt:LocalDate?
        get() {
            return paymentTime?.let {
                kotlin.runCatching {
                    jacksonObjectMapper().readValue(this.paymentTime, LocalDate::class.java)
                }.onFailure {
                    println("Failed to parse date -> ${it.message}")
                }.getOrNull()

            }
        }

}