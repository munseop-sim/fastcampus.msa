package ms2709.payservice.member.adapter.out.persistence.entity

import jakarta.persistence.*

/**
 *
 * 멤버십 정보를 저장하고 조회하기 위한 JPA Entity
 *
 *  MembershipJpaEntity
 * @author 심문섭
 * @version 1.0
 * @since 2024-04-29 9:04 PM
 */
@Table(name = "MEMBERSHIP")
@Entity
class MembershipJpaEntity : Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBERSHIP_ID")
    var membershipId: Long? = null

    @Column(name="MEMBERSHIP_NAME")
    var name: String? = null

    @Column(name="MEMBERSHIP_ADDRESS")
    var address:String? = null

    @Column(name="MEMBERSHIP_EMAIL")
    var email:String? =null

    @Column(name="MEMBERSHIP_IS_VALID")
    var isValid:Boolean? = null

    @Column(name="MEMBERSHIP_IS_CORP")
    var isCorp:Boolean? = null

    @Column(name="MEMBERSHIP_REFRESH_TOKEN")
    var refreshToken: String? = null

    constructor(){}

    constructor(name: String?, address: String?, email: String?, isValid: Boolean?, isCorp: Boolean?, refreshToken:String) {
        this.name = name
        this.address = address
        this.email = email
        this.isValid = isValid
        this.isCorp = isCorp
        this.refreshToken = refreshToken
    }

    constructor(
        membershipId: Long?,
        name: String?,
        address: String?,
        email: String?,
        isValid: Boolean?,
        isCorp: Boolean?,
        refreshToken: String?
    ) {
        this.membershipId = membershipId
        this.name = name
        this.address = address
        this.email = email
        this.isValid = isValid
        this.isCorp = isCorp
        this.refreshToken = refreshToken
    }

    public override fun clone(): MembershipJpaEntity {
        return MembershipJpaEntity(
            this.membershipId,
            this.name,
            this.address,
            this.email,
            this.isValid,
            this.isCorp,
            this.refreshToken
        )
    }
}