package ms2709.payservice.remittance.adapter.out.service.money

import ms2709.global.ExternalSystemAdapter
import ms2709.payservice.remittance.application.port.out.money.MoneyInfo
import ms2709.payservice.remittance.application.port.out.money.MoneyPort

/**
 *
 * 클래스 설명
 *
 * @class MoneyServiceAdapter
 * @author 심문섭
 * @version 1.0
 * @since 2024-05-25 오전 8:23
 */
@ExternalSystemAdapter
class MoneyServiceAdapter: MoneyPort {
    override fun getMoneyInfo(membershipId: String): MoneyInfo {
        //todo - money-service의 계좌장보를 조회하는 API 호출 구현
        return MoneyInfo(membershipId, Int.MAX_VALUE)
    }

    override fun requestMoneyRecharging(membershipId: String, amount: Int): Boolean {
        //todo - money-service의 계좌 충전 API 호출 구현
        return true
    }

    override fun requestMoneyIncrease(membershipId: String, amount: Int): Boolean {
        //todo - money-service의 계좌 잔액 증가 API 호출 구현
        return true
    }

    override fun requestMoneyDecrease(membershipId: String, amount: Int): Boolean {
        //todo - money-service의 계좌 잔액 감소 API 호출 구현
        return true
    }
}