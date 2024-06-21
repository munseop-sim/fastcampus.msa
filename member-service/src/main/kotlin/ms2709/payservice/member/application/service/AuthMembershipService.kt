package ms2709.payservice.member.application.service

import ms2709.global.UseCase
import ms2709.payservice.member.adapter.out.persistence.MembershipMapper
import ms2709.payservice.member.application.port.`in`.*
import ms2709.payservice.member.application.port.out.AuthMembershipPort
import ms2709.payservice.member.application.port.out.FindMembershipPort
import ms2709.payservice.member.application.port.out.ModifyMembershipPort
import ms2709.payservice.member.domain.JwtToken
import ms2709.payservice.member.domain.Membership
import org.springframework.transaction.annotation.Transactional

/**
 *
 * 클래스 설명
 *
 * @class AuthMembershipService
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-21 오후 11:24
 */
@UseCase
@Transactional
class AuthMembershipService(
    private val authMembershipPort: AuthMembershipPort,
    private val findMembershipPort: FindMembershipPort,
    private val modifyMembershipPort: ModifyMembershipPort,
    private val membershipMapper: MembershipMapper
):AuthMembershipUseCase {
    override fun loginMembership(command: LoginMembershipCommand): JwtToken {
        val entity =findMembershipPort.findMembership(command.membershipId.run {
            Membership.MembershipId(this)
        }) ?: throw IllegalArgumentException("not found membership")

        if(entity.isValid == false){
            throw IllegalArgumentException("not valid membership")
        }

        val membershipId = entity.membershipId?.let { it.toString() } ?: throw IllegalArgumentException("not found membershipId")
        val token = JwtToken.create(
            JwtToken.MembershipId(membershipId),
            JwtToken.AccessToken(authMembershipPort.generateAccessToken(Membership.MembershipId(membershipId))),
            JwtToken.RefreshToken(authMembershipPort.generateRefreshToken(Membership.MembershipId(membershipId)))
        )
        modifyMembershipPort.modifyRefreshToken(membershipId.run { Membership.MembershipId(this) }, token.refreshToken)

        return token

    }

    override fun refreshToken(command: RefreshTokenCommand): JwtToken {
        if(authMembershipPort.validateToken(command.refreshToken).not()){
            throw IllegalArgumentException("not valid refreshToken")
        }

        val membershipId = authMembershipPort.getMembershipId(command.refreshToken).value
        val entity = findMembershipPort.findMembership(membershipId.run {
            Membership.MembershipId(this)

        }) ?: throw IllegalArgumentException("not found membership")
        if(entity.isValid == false){
            throw IllegalArgumentException("not valid membership")
        }
        if(entity.refreshToken != command.refreshToken){
            throw IllegalArgumentException("not valid refreshToken")
        }

        val token = JwtToken.create(
            JwtToken.MembershipId(membershipId),
            JwtToken.AccessToken(authMembershipPort.generateAccessToken(Membership.MembershipId(membershipId))),
            JwtToken.RefreshToken(authMembershipPort.generateRefreshToken(Membership.MembershipId(membershipId)))
        )
        modifyMembershipPort.modifyRefreshToken(Membership.MembershipId(membershipId), command.refreshToken)

        return token

    }

    override fun validateToken(toCommand: ValidateTokenCommand): Boolean {
        return authMembershipPort.validateToken(toCommand.accessToken)
    }

    override fun getMembershipByToken(command: GetMembershipByTokenCommand): Membership {
        val membershipId = authMembershipPort.getMembershipId(command.accessToken)
        val entity = findMembershipPort.findMembership(membershipId) ?: throw IllegalArgumentException("not found membership")
        return membershipMapper.mapToDomainEntity(entity)
    }
}