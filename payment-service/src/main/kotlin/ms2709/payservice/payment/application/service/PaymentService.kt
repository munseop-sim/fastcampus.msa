package ms2709.payservice.payment.application.service

import ms2709.global.UseCase
import ms2709.payservice.payment.application.port.`in`.*
import ms2709.payservice.payment.application.port.out.*
import ms2709.payservice.payment.domain.Payment
import org.springframework.transaction.annotation.Transactional

/**
 *
 * 클래스 설명
 *
 *  PaymentService
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-02 오후 11:24
 */
@UseCase
@Transactional
class PaymentService(
    private val createPaymentPort: CreatePaymentPort,
    private val getMembershipPort: GetMembershipPort,
    private val getRegisteredBankAccountPort: GetRegisteredBankAccountPort,
    private val getPaymentStatusListPort: GetPaymentStatusListPort,
    private val getPaymentPort: GetPaymentPort
): RequestPaymentUseCase, StatusListUseCase {
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

    override fun finishSettlement(finishSettlementCommand: FinishSettlementCommand) {
        return getPaymentPort.getPayment(finishSettlementCommand.paymentId)?.let {
            if(it.paymentStatus == 0){
                it.paymentStatus = 2
            }


        } ?: throw IllegalArgumentException("결제 정보가 없습니다.")
    }

    override fun getNormalStatusPaymentList(command: NormalStatusPaymentListCommand): List<Payment> {
        return getPaymentStatusListPort.getNormalStatusPaymentList(command.startDate, command.endDate)
    }
}