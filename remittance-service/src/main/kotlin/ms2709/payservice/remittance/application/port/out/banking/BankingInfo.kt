package ms2709.payservice.remittance.application.port.out.banking

import ms2709.payservice.remittance.domain.RemittanceRequest

/**
 *
 * 클래스 설명
 *
 *  BankingInfo
 * @author 심문섭
 * @version 1.0
 * @since 2024-05-25 오전 7:44
 */
data class BankingInfo (
    val bankName:String,
    val bankAccountNumber: String,
    val isValid:Boolean
)