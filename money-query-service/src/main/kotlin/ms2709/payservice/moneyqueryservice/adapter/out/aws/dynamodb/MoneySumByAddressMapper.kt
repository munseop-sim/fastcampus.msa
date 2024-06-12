package ms2709.payservice.moneyqueryservice.adapter.out.aws.dynamodb

import software.amazon.awssdk.services.dynamodb.model.AttributeValue

/**
 *
 * 클래스 설명
 *
 * @class MoneySumByAddressMapper
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-12 오전 10:27
 */
class MoneySumByAddressMapper {
    fun mapToMoneySumByAddress(item:Map<String, AttributeValue>): MoneySumByAddress {
        return MoneySumByAddress(
            PK = item["PK"]?.s(),
            SK = item["SK"]?.s(),
            balance = item["balance"]?.n()?.toInt()
        )
    }
}