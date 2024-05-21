package ms2709.payservice.money.application.port.out;

import ms2709.global.kafka.RechargingMoneyTask;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class SendRechargingMoneyTaskPort
 * @since 2024-05-21 오전 8:23
 */
public interface SendRechargingMoneyTaskPort {
    void sendRechargingMoneyTaskPort(
            RechargingMoneyTask task
    );
}