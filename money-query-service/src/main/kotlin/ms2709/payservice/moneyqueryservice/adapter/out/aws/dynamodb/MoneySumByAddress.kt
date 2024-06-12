package ms2709.payservice.moneyqueryservice.adapter.out.aws.dynamodb

/**
 *
 * 클래스 설명
 *
 * @class MoneySumByAddress
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-12 오전 10:26
 */
data class MoneySumByAddress(
    val PK: String?,
    val SK: String?,
    val balance: Int?
)