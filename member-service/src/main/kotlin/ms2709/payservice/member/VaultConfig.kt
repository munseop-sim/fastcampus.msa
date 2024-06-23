package ms2709.payservice.member

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.vault.authentication.TokenAuthentication
import org.springframework.vault.client.VaultEndpoint
import org.springframework.vault.core.VaultTemplate
import org.springframework.vault.support.VaultToken

/**
 *
 * 클래스 설명
 *
 * @class ValutConfig
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-23 오후 2:30
 */
@Configuration
class VaultConfig {
    @Value("\${spring.cloud.vault.token}")
    private val vaultToken: String? = null

    @Value("\${spring.cloud.vault.scheme}")
    private val vaultScheme: String? = null

    @Value("\${spring.cloud.vault.host}")
    private val vaultHost: String? = null

    @Value("\${spring.cloud.vault.port}")
    private val vaultPort: Int? = null


    @Bean
    fun vaultTemplate(): VaultTemplate {
        val endpoint = VaultEndpoint.create(vaultHost!!, vaultPort!!)
        endpoint.scheme = vaultScheme!!

        return VaultTemplate(endpoint, TokenAuthentication(VaultToken.of(vaultToken!!)))
    }

}