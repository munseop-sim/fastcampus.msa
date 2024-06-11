package ms2709.payservice.remittance.application.service

import ms2709.global.UseCase
import ms2709.payservice.remittance.adapter.out.persistence.RemittanceRequestMapper
import ms2709.payservice.remittance.application.port.`in`.RequestRemittanceCommand
import ms2709.payservice.remittance.application.port.`in`.RequestRemittanceUseCase
import ms2709.payservice.remittance.application.port.out.RequestRemittancePort
import ms2709.payservice.remittance.application.port.out.banking.BankingPort
import ms2709.payservice.remittance.application.port.out.membership.MembershipPort
import ms2709.payservice.remittance.application.port.out.money.MoneyPort
import ms2709.payservice.remittance.domain.RemittanceRequest
import org.springframework.transaction.annotation.Transactional
import kotlin.math.ceil


/**
 *
 * 클래스 설명
 *
 *  RequestRemittanceService
 * @author 심문섭
 * @version 1.0
 * @since 2024-05-25 오전 6:50
 */
@UseCase
@Transactional
class RequestRemittanceService(
    private val requestRemittancePort: RequestRemittancePort,
    private val mapper:RemittanceRequestMapper,
    private val membershipPort:MembershipPort,
    private val bankingPort: BankingPort,
    private val moneyPort: MoneyPort
) : RequestRemittanceUseCase {
    override fun requestRemittance(command: RequestRemittanceCommand): RemittanceRequest {
        // 0. 송금 요청 상태를 시작 상태로 기록 (persistence layer)
        val entity = requestRemittancePort.createRemittanceRequestHistory(command)
        // 1. from 멤버십 상태 확인 (membership Svc)
        membershipPort.getMembershipStatus(command.fromMembershipId).run {
            if(this.isValid.not()){
                throw RuntimeException("보내는 계좌의 멤버십(fromMembership)이 유효하지 않습니다.")
            }
        }
        // 2. 잔액 존재하는지 확인 (money svc)
        val moneyInfo = moneyPort.getMoneyInfo(command.fromMembershipId)

        // 잔액이 충분치 않은 경우. -> 충전이 필요한 경우
        if(moneyInfo.balance < command.amount){
            //만원단위로 올림
            val rechargeAmount = ceil((command.amount - moneyInfo.balance) / 10000.0)
                .toInt() * 10000

            //충전 요청
            moneyPort.requestMoneyRecharging(command.fromMembershipId, rechargeAmount).run{
                if(this.not()){
                    throw RuntimeException("잔액이 부족하며 충전 요청에 실패했습니다.")
                }
            }
        }
        // 3. 송금 타입 (고객/은행)
        when(command.remittanceType){
            0 -> {
                val remittanceResult1 = moneyPort.requestMoneyDecrease(command.fromMembershipId, command.amount)
                val remittanceResult2 = moneyPort.requestMoneyIncrease(command.toMembershipId, command.amount)
                if (!remittanceResult1 || !remittanceResult2) {
                    throw RuntimeException("송금 요청에 실패했습니다.")
                }
            }
            1->{
                val remittanceResult = bankingPort.requestFirmbanking(
                    command.toBankName,
                    command.toBankAccountNumber,
                    command.amount
                )
                if (!remittanceResult) {
                    throw RuntimeException("송금 요청에 실패했습니다.(은행 송금 실패)")
                }
            }
            else->{
                throw RuntimeException("송금 타입이 잘못되었습니다.")
            }
        }

        entity.remittanceStatus = "success"
        val result = requestRemittancePort.saveRemittanceRequestHistory(entity)
        if (result) {
            return mapper.mapToEntity(entity)
        }else{
            throw RuntimeException("송금요청 데이터 저장에 실패하였습니다.")
        }
    }
}