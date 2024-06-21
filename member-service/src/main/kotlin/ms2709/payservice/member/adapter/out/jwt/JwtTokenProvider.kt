package ms2709.payservice.member.adapter.out.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm

import ms2709.payservice.member.application.port.out.AuthMembershipPort
import ms2709.payservice.member.domain.Membership
import org.springframework.stereotype.Component
import org.slf4j.LoggerFactory
import java.util.*

/**
 *
 * 클래스 설명
 *
 * @class JwtTokenProvider
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-21 오후 11:36
 */
@Component
class JwtTokenProvider:AuthMembershipPort {
    private val log  = LoggerFactory.getLogger(JwtTokenProvider::class.java)

    private val jwtSecretKey:String = "secret"
    private val accessTokeExpireSecond:Long
    private val refreshTokenExpireSecond:Long
    private val memberShipIdClaimName: String
        get() = "MEMBERSHIP_ID"

    init {
        accessTokeExpireSecond = 60
        refreshTokenExpireSecond = 120
    }

    private fun jwtAlgorithm() = Algorithm.HMAC256(jwtSecretKey)

    override fun generateAccessToken(membershipId: Membership.MembershipId): String {
        val now = Date()
        val expire = Date(now.time+(accessTokeExpireSecond*1000))

        val token = JWT.create()
            .withClaim(this.memberShipIdClaimName, membershipId.value)
            .withIssuedAt(now)
            .withExpiresAt(expire)
            .sign(jwtAlgorithm())
        return token
    }

    override fun generateRefreshToken(membershipId: Membership.MembershipId): String {
        val now = Date()
        val expire = Date(now.time+(refreshTokenExpireSecond*1000))

       val token = JWT.create()
            .withClaim(this.memberShipIdClaimName, membershipId.value)
            .withIssuedAt(now)
            .withExpiresAt(expire)
            .sign(jwtAlgorithm())
        return token
    }

    override fun validateToken(accessToken: String): Boolean {
        return kotlin.runCatching {
            val verifier = JWT.require(jwtAlgorithm()).build()
            verifier.verify(accessToken)
            true
        }.onFailure {
            log.error("validateToken Error -> {}",it.message)
        }.getOrNull() ?: false

    }

    override fun getMembershipId(token: String): Membership.MembershipId {
        return kotlin.runCatching {
            val decodedJwt = JWT.require(jwtAlgorithm()).build().verify(token)
            decodedJwt.getClaim(this.memberShipIdClaimName).asString()!!.run {
                Membership.MembershipId(this)
            }
        }.onFailure {
            log.error("getMembershipId Error -> {}",it.message)
            throw RuntimeException("Invalid Token")
        }.getOrThrow()
    }
}