package ms2709.payservice.remittance.adapter.out.persistence.entity

import jakarta.persistence.*

/**
 *
 * 클래스 설명
 *
 *  RemittanceRequestJpaEntity
 * @author 심문섭
 * @version 1.0
 * @since 2024-05-25 오전 6:57
 */
@Entity
@Table(name = "remittance")
class RemittanceRequestJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var remittanceRequestId:Long? = null

    var fromMembershipId:String? = null

    var toMembershipId:String? = null

    var toBankName:String? = null

    var toBankAccountNumber:String? = null

    var remittanceType:Int? = null

    var amount:Int? = null

    var remittanceStatus:String? = null



    constructor(
        fromMembershipId: String?,
        toMembershipId: String?,
        toBankName: String?,
        toBankAccountNumber: String?,
        remittanceType: Int?,
        amount: Int?
    ) {
        this.fromMembershipId = fromMembershipId
        this.toMembershipId = toMembershipId
        this.toBankName = toBankName
        this.toBankAccountNumber = toBankAccountNumber
        this.remittanceType = remittanceType
        this.amount = amount

    }

    constructor()
}