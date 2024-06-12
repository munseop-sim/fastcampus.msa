package ms2709.payservice.moneyqueryservice.domain

/**
 *
 * 클래스 설명
 *
 * @class MoneySumByRegion
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-12 오전 8:34
 */
class MoneySumByRegion {
    private val moneySumByRegionId:String
    private val regionName:String
    private val moneySum:Long

    private constructor(moneySumByRegionId: String, regionName: String, moneySum: Long) {
        this.moneySumByRegionId = moneySumByRegionId
        this.regionName = regionName
        this.moneySum = moneySum
    }

    fun getMoneySumByRegionId(): String {
        return moneySumByRegionId
    }
    fun getRegionName(): String {
        return regionName
    }
    fun getMoneySum(): Long {
        return moneySum
    }

    data class MoneySumByRegionId(val value:String)
    data class RegionName(val value:String)
    data class MoneySum(val value:Int)

    companion object{
        fun create(moneySumByRegionId: MoneySumByRegionId, regionName: RegionName, moneySum: MoneySum): MoneySumByRegion {
            return MoneySumByRegion(
                moneySumByRegionId.value,
                regionName.value,
                moneySum.value.toLong()
            )
        }
    }
}