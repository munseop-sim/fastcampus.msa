package ms2709.payservice.member.adapter.out.vault

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.vault.core.VaultKeyValueOperationsSupport
import org.springframework.vault.core.VaultTemplate

/**
 *
 * 클래스 설명
 *
 * @class VaultAdapter
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-23 오후 2:20
 */
@Component
class VaultAdapter(
    private val vaultTemplate: VaultTemplate
) :VaultUseCase {
    private lateinit var aesProvider:AESProvider
    private val log = LoggerFactory.getLogger(VaultAdapter::class.java)
    init {
        val ops = vaultTemplate.opsForKeyValue("kv-v1/data/encrypt", VaultKeyValueOperationsSupport.KeyValueBackend.KV_2)
        val key = ops["dbkey"]?.data?.get("key")?.let {
            it.toString().padEnd(32, '0')
            //aes 암호화에서 사용되는 키의 길이는 16, 24, 32byte여야 한다.
            //따라서 길이가 32byte가 되도록 0으로 패딩처리한다.
        } ?: throw RuntimeException("key not found")

        log.info("vault key: $key")
        aesProvider = AESProvider(key)
    }

    override fun encrypt(plaintText: String): String {
        return runCatching{
            aesProvider.encrypt(plaintText)
        }.onFailure {
            log.error("encrypt text exception -> {}",it.message)
        }.getOrThrow()

    }

    override fun decrypt(encryptText: String): String {
        return runCatching {
            aesProvider.decrypt(encryptText)
        }.onFailure {
            log.error("decrypt text exception -> {}",it.message)
        }.getOrThrow()
    }
}

