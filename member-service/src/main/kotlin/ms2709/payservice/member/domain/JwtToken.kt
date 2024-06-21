package ms2709.payservice.member.domain

/**
 *
 * 클래스 설명
 *
 * @class JwtToken
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-21 오후 10:19
 */
class JwtToken {
    val membershipId:String
    val accessToken:String
    val refreshToken:String

    private constructor(membershipId: String, accessToken: String, refreshToken: String) {
        this.membershipId = membershipId
        this.accessToken = accessToken
        this.refreshToken = refreshToken
    }


    data class MembershipId(val value: String) {
        init {
            require(value.isNotEmpty()) { "membershipId must not be empty" }
        }
    }
    data class AccessToken(val value: String) {
        init {
            require(value.isNotEmpty()) { "accessToken must not be empty" }
        }
    }

    data class RefreshToken(val value: String) {
        init {
            require(value.isNotEmpty()) { "refreshToken must not be empty" }
        }
    }

    companion object {
        fun create(membershipId: MembershipId,
                   accessToken: AccessToken,
                   refreshToken: RefreshToken
        ): JwtToken {
            return JwtToken(
                membershipId.value,
                accessToken.value,
                refreshToken.value
            )
        }
    }
}