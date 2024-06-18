package ms2709.payservice.settlement.batch.tasklet

import ms2709.payservice.settlement.port.out.GetRegisteredBankAccountPort
import ms2709.payservice.settlement.port.out.PaymentPort
import org.slf4j.LoggerFactory
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.stereotype.Component
import java.time.LocalDate

/**
 *
 * 클래스 설명
 *
 * @class SettlementTasklet
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-18 오전 8:16
 */
@Component
class SettlementTasklet(
    private val getRegisteredBankAccountPort: GetRegisteredBankAccountPort,
    private val paymentPort: PaymentPort
) : Tasklet{
    private val log = LoggerFactory.getLogger(SettlementTasklet::class.java)
    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus? {
        // 1. payment service 에서 결제 완료된 결제 내역을 조회한다.
        val now = LocalDate.now()
        val before = now.minusDays(10)

        val paymentList = paymentPort.getNormalStatusPayments(before, now)
        if(paymentList.isEmpty()){
            log.info("No payment to settle")
            return RepeatStatus.FINISHED
        }
        // 2. 각 결제 내역의 franchiseId 에 해당하는 멤버십 정보(membershipId)에 대한
        // 뱅킹 정보(계좌번호) 를 가져와서

        val franchiseIdBankAccountMap = HashMap<String,FirmbankingRequestInfo>()
        paymentList.forEach {
            val franchiseId = it.franchiseId!!
            val registeredBankAccount = getRegisteredBankAccountPort.getRegisteredBankAccount(franchiseId)
            if(registeredBankAccount == null){
                log.error("Failed to get registered bank account. franchiseId: $franchiseId")
                return@forEach
            }
            franchiseIdBankAccountMap[it.franchiseId!!] = FirmbankingRequestInfo(registeredBankAccount.bankName!!, registeredBankAccount.bankAccountNumber!!)
        }

        // 3. 각 franchiseId 별로, 정산 금액을 계산해주고(수수료는 제하지 않음)
        paymentList.forEach {payment->
            franchiseIdBankAccountMap[payment.franchiseId!!]?.let {
                val fee = payment.franchiseFeeRate?.toDouble()
                val caculatedPrice = (100 - (fee ?: 0.0)) *  ((payment.requestPrice ?: 0) * 100)
                it.moneyAmount += caculatedPrice.toInt()
            }
        }

        // 4. 계산된 금액을 펌뱅킹 요청해주고
        for (firmbankingRequestInfo in franchiseIdBankAccountMap.values) {
            getRegisteredBankAccountPort.requestFirmbanking(firmbankingRequestInfo.bankName, firmbankingRequestInfo.bankAccountNumber, firmbankingRequestInfo.moneyAmount)
        }


        // 5. 정산 완료된 결제 내역은 정산 완료 상태로 변경
        paymentList.forEach {
            paymentPort.finishSettlement(it.paymentId!!)
        }


        return RepeatStatus.FINISHED
    }
}