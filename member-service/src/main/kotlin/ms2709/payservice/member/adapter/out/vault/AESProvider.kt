package ms2709.payservice.member.adapter.out.vault

import java.security.SecureRandom
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec


/**
 *
 * 클래스 설명
 *
 * @class AESProvider
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-23 오후 2:18
 */

class AESProvider(key: String) {
    private val secretKey = SecretKeySpec(key.toByteArray(), AESProvider.Companion.ALGORITHM)

    @Throws(Exception::class)
    fun encrypt(plainText: String): String {
        val cipher: Cipher = Cipher.getInstance(AESProvider.Companion.TRANSFORMATION)
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        val encryptedBytes: ByteArray = cipher.doFinal(plainText.toByteArray())
        return Base64.getEncoder().encodeToString(encryptedBytes)
    }

    @Throws(Exception::class)
    fun decrypt(encryptedText: String): String {
        val cipher: Cipher = Cipher.getInstance(AESProvider.Companion.TRANSFORMATION)
        cipher.init(Cipher.DECRYPT_MODE, secretKey)
        val decodedBytes: ByteArray = Base64.getDecoder().decode(encryptedText)
        val decryptedBytes: ByteArray = cipher.doFinal(decodedBytes)
        return String(decryptedBytes)
    }

    companion object {
        private const val ALGORITHM = "AES"
        private const val TRANSFORMATION = "AES/ECB/PKCS5Padding" // ECB mode, PKCS5Padding

        // helper method for AES key generation
        // 16byte = 128bit
        // 24byte = 192bit
        // 32byte = 256bit
        fun generateRandomString(length: Int): String {
            val randomBytes = ByteArray(length)
            SecureRandom().nextBytes(randomBytes)
            return Base64.getEncoder().encodeToString(randomBytes)
        }
    }
}
