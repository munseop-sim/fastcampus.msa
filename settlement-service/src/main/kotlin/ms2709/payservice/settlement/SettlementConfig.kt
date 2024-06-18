package ms2709.payservice.settlement

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling

/**
 *
 * 클래스 설명
 *
 * @class SettlementConfig
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-17 오후 10:17
 */
@Configuration
@EnableScheduling
@ComponentScan("ms2709.global")
class SettlementConfig {

}