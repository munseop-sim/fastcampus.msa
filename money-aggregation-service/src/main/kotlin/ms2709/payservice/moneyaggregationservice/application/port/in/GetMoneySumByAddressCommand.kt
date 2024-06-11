package ms2709.payservice.moneyaggregationservice.application.port.`in`

import jakarta.validation.constraints.NotBlank
import ms2709.global.SelfValidating

/**
 *
 * 클래스 설명
 *
 * @class GetMoneySumByAddressCommand
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-11 오후 12:50
 */
class GetMoneySumByAddressCommand(
    @NotBlank(message = "주소는 필수입력입니다.") private val address: String
) : SelfValidating<GetMoneySumByAddressCommand>() {
    init {
        this.validateSelf()
    }

    fun getAddress(): String {
        return address
    }
}