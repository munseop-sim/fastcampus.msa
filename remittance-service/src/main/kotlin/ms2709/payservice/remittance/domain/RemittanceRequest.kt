package ms2709.payservice.remittance.domain

/**
 *
 * 클래스 설명
 *
 * @class RemittanceRequest
 * @author 심문섭
 * @version 1.0
 * @since 2024-05-25 오전 6:39
 */
class RemittanceRequest {

    private val remittanceRequestId:String
    private val remittanceFromMembershipId:String
    private val toBankName:String
    private val toBankAccountNumber:String
    private val remittanceType:Int
    private val amount:Int
    private val remittanceStatus:String

    fun getRemittanceRequestId():String {
        return remittanceRequestId
    }
    fun getRemittanceFromMembershipId():String {
        return remittanceFromMembershipId
    }
    fun getToBankName():String {
        return toBankName
    }
    fun getToBankAccountNumber():String {
        return toBankAccountNumber
    }
    fun getRemittanceType():Int {
        return remittanceType
    }
    fun getAmount():Int {
        return amount
    }
    fun getRemittanceStatus():String {
        return remittanceStatus
    }

    private constructor(
        remittanceRequestId: RemittanceRequestId,
        remittanceFromMembershipId: RemittanceFromMembershipId,
        toBankName: ToBankName,
        toBankAccountNumber: ToBankAccountNumber,
        remittanceType: RemittanceType,
        amount: Amount,
        remittanceStatus: RemittanceStatus
    ) {
        this.remittanceRequestId = remittanceRequestId.value
        this.remittanceFromMembershipId = remittanceFromMembershipId.value
        this.toBankName = toBankName.value
        this.toBankAccountNumber = toBankAccountNumber.value
        this.remittanceType = remittanceType.value
        this.amount = amount.value
        this.remittanceStatus = remittanceStatus.value
    }

    class RemittanceRequestId(val value:String){
        init {
            require(value.isNotEmpty()) { "RemittanceRequestId must not be empty" }
        }
    }

    class RemittanceFromMembershipId(val value:String){
        init {
            require(value.isNotEmpty()) { "RemittanceFromMembershipId must not be empty" }
        }
    }

    class ToBankName(val value:String){
        init {
            require(value.isNotEmpty()) { "ToBankName must not be empty" }
        }
    }
    class ToBankAccountNumber(val value:String){
        init {
            require(value.isNotEmpty()) { "ToBankAccountNumber must not be empty" }
        }
    }

    class RemittanceType(val value:Int){
        init {
            require(value >= 0) { "RemittanceType must not be empty" }
        }
    }

    class Amount(val value:Int){
        init {
            require(value >= 0) { "Amount must not be empty" }
        }
    }
    class RemittanceStatus(val value:String){
        init {
            require(value.isNotEmpty()) { "RemittanceStatus must not be empty" }
        }
    }

    companion object{
        fun create(
            remittanceRequestId: RemittanceRequestId,
            remittanceFromMembershipId: RemittanceFromMembershipId,
            toBankName: ToBankName,
            toBankAccountNumber: ToBankAccountNumber,
            remittanceType: RemittanceType,
            amount: Amount,
            remittanceStatus: RemittanceStatus
        ): RemittanceRequest {
            return RemittanceRequest(
                remittanceRequestId,
                remittanceFromMembershipId,
                toBankName,
                toBankAccountNumber,
                remittanceType,
                amount,
                remittanceStatus
            )
        }
    }
}
