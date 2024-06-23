package ms2709.payservice.member.adapter.out.vault

/**
 *
 * 클래스 설명
 *
 * @class VaultUseCase
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-23 오후 10:08
 */
interface VaultUseCase {
    fun encrypt(plaintText: String): String
    fun decrypt(encryptText: String): String
}