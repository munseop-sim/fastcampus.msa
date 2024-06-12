package ms2709.payservice.moneyqueryservice.adapter.axon

import ms2709.global.axon.event.RequestFirmbankingFinishedEvent
import ms2709.payservice.moneyqueryservice.application.port.out.GetMemberAddressInfoPort
import ms2709.payservice.moneyqueryservice.application.port.out.InsertMoneyIncreaseEventByAddress
import org.axonframework.eventhandling.EventHandler
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

/**
 *
 * 클래스 설명
 *
 * @class MoneyIncreaseEventHandler
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-12 오전 10:59
 */
@Component
class MoneyIncreaseEventHandler {
    private val log = LoggerFactory.getLogger(MoneyIncreaseEventHandler::class.java)

    @EventHandler
    fun handler(event: RequestFirmbankingFinishedEvent,
                getMemberAddressInfoPort: GetMemberAddressInfoPort,
                insertMoneyIncreaseEventByAddress: InsertMoneyIncreaseEventByAddress){
        log.info("Money Increase Event Received: $event")
        val memberAddressInfo = getMemberAddressInfoPort.getMemberAddressInfo(event.membershipId) ?: return
        val address = memberAddressInfo.address ?: return
        val moneyIncrease = event.moneyAmount
        log.info("DYNAMODB INSERT: $address, $moneyIncrease")
        insertMoneyIncreaseEventByAddress.insertMoneyIncreaseEventByAddress(address, moneyIncrease)
    }
}