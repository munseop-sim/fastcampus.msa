package ms2709.payservice.payment.application.service

import ms2709.global.UseCase
import ms2709.payservice.payment.application.port.`in`.RequestPaymentCommand
import ms2709.payservice.payment.application.port.`in`.RequestPaymentUseCase
import ms2709.payservice.payment.application.port.out.CreatePaymentPort
import ms2709.payservice.payment.application.port.out.GetMembershipPort
import ms2709.payservice.payment.application.port.out.GetRegisteredBankAccountPort
import ms2709.payservice.payment.domain.Payment
import org.springframework.transaction.annotation.Transactional

/**
 *
 * 클래스 설명
 *
 * @class PaymentService
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-02 오후 11:24
 */
@UseCase
@Transactional
class PaymentService(
    private val createPaymentPort: CreatePaymentPort,
    private val getMembershipPort: GetMembershipPort,
    private val getRegisteredBankAccountPort: GetRegisteredBankAccountPort
): RequestPaymentUseCase {
    override fun requestPayment(requestPaymentCommand: RequestPaymentCommand): Payment {
        //충전도, 멤버십, 머니 유효성 확인 필요
        getMembershipPort.getMembership(requestPaymentCommand.requestMemberId)
        getRegisteredBankAccountPort.getRegisteredBankAccount(requestPaymentCommand.requestMemberId)


        return createPaymentPort.createPayment(
            requestMemberId = requestPaymentCommand.requestMemberId,
            requestPrice = requestPaymentCommand.requestPrice,
            franchiseId = requestPaymentCommand.franchiseId,
            franchiseFeeRate = requestPaymentCommand.franchiseFeeRate
        )
    }
}