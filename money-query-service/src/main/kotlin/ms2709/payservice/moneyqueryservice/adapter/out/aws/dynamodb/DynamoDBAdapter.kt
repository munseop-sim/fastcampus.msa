package ms2709.payservice.moneyqueryservice.adapter.out.aws.dynamodb

import ms2709.payservice.moneyqueryservice.application.port.`in`.QueryMoneySumByRegionQueryCommand
import ms2709.payservice.moneyqueryservice.application.port.out.GetMoneySumByAddressPort
import ms2709.payservice.moneyqueryservice.application.port.out.InsertMoneyIncreaseEventByAddress
import ms2709.payservice.moneyqueryservice.domain.MoneySumByRegion
import org.axonframework.queryhandling.QueryHandler
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import software.amazon.awssdk.services.dynamodb.model.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

/**
 *
 * AWS DynamoDB를 사용하여 돈을 증가시키는 이벤트를 저장하고, 지역별 돈의 합계를 조회하는 어댑터 (CQRS)
 * AWS IAM에서 유저에게 DynamoDB Full Access 권한을 부여해야 함(실무에서는 안됨)
 * DynamoDB의 권한을 가진 유저에서 엑세스 토큰(ACCESS_KEY, SECRET_KEY) 발급 후에 해당 토큰으로 접근해야 함
 * 실습 종료후에는 AWS에서 엑세스 토큰을 삭제(접근권한 제거)하고 DynamoDB Table을 삭제해야 비용이 청구되지 않음
 *
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-12 오전 10:22
 */
@Component
class DynamoDBAdapter : GetMoneySumByAddressPort, InsertMoneyIncreaseEventByAddress{
    private val log = LoggerFactory.getLogger(DynamoDBAdapter::class.java)

    /**
     * AWS DynamoDB Table 이름
     */
    private val TABLE_NAME = "MoneyIncreaseEventByRegion"

    /**
     * AWS DynamoDB 접근을 위한 Access Key
     */
    private val ACCESS_KEY = ""

    /**
     * AWS DynamoDB 접근을 위한 Secret Key
     */
    private val SECRET_KEY = ""

    private lateinit var dynamoDbClient: DynamoDbClient
    private val moneySumByAddressMapper: MoneySumByAddressMapper

    private val is_access_aws = false

    init {
        this.moneySumByAddressMapper = MoneySumByAddressMapper()
        if(is_access_aws.not()){
            log.info("AWS에 연결되어 있지 않으므로 DynamoDB에 저장하지 않음")
        }else{
            this.dynamoDbClient = AwsBasicCredentials.create(ACCESS_KEY, SECRET_KEY).run {
                DynamoDbClient.builder()
                    .region(Region.AP_NORTHEAST_2)
                    .credentialsProvider(StaticCredentialsProvider.create(this))
                    .build()
            }
        }

    }

    override fun getMoneySumByAddress(address: String): Int {
        if(is_access_aws.not()){
            log.info("AWS에 연결되어 있지 않으므로 0 반환")
            return 0
        }
        return getItem(address, "-1")?.balance ?: 0
    }

    override fun insertMoneyIncreaseEventByAddress(address: String, money: Int) {
        if(is_access_aws.not()){
            log.info("AWS에 연결되어 있지 않으므로 DynamoDB에 저장하지 않음")
            return
        }
        val rawPk = "${address}#${LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))}"
        putItem(pk = rawPk,
            sk = money.toString(),
            balance = money
        )

        val summaryPk = "$rawPk#summary"
        val summarySk = "-1"
        getItem(summaryPk, summarySk)?.let {
            updateItem(pk = summaryPk, sk = summarySk, balance = (it.balance?:0) + money)
        } ?: {
            putItem(pk = summaryPk, sk = summarySk, balance = money)
        }

        val regionPk = address
        val regionSk = "-1"
        getItem(regionPk, regionSk)?.let {
            updateItem(pk = regionPk, sk = regionSk, balance = (it.balance?:0) + money)
        } ?: {
            putItem(pk = regionPk, sk = regionSk, balance = money)
        }

    }

    private fun putItem(pk:String, sk:String, balance:Int){
        kotlin.runCatching {
            val requestParam = mapOf(
                "PK" to AttributeValue.builder().s(pk).build(),
                "SK" to AttributeValue.builder().s(sk).build(),
                "balance" to AttributeValue.builder().n(balance.toString()).build()
            )
            val request = PutItemRequest.builder()
                .tableName(TABLE_NAME)
                .item(requestParam)
                .build()
            dynamoDbClient.putItem(request)
        }.onFailure {
            log.error("Failed to put item to DynamoDB -->", it)
            it.printStackTrace()
        }
    }

    private fun getItem(pk:String, sk:String): MoneySumByAddress? {
        return kotlin.runCatching {
            val requestParam = mapOf(
                "PK" to AttributeValue.builder().s(pk).build(),
                "SK" to AttributeValue.builder().s(sk).build()
            )
            val request = GetItemRequest.builder()
                .tableName(TABLE_NAME)
                .key(requestParam)
                .build()
            dynamoDbClient.getItem(request).run {
                if(this.hasItem().not()){
                    null
                }
                moneySumByAddressMapper.mapToMoneySumByAddress(this.item())
            }
        }.onFailure {
            log.error("Failed to get item from DynamoDB -->", it)
            it.printStackTrace()
        }.getOrNull()
    }

    private fun queryItem(id:String){
        kotlin.runCatching {
            val requestParam = mapOf(
                "PK" to Condition.builder()
                    .attributeValueList(AttributeValue.builder().s(id).build())
                    .comparisonOperator(ComparisonOperator.EQ)
                    .build()
            )

            val request = QueryRequest.builder()
                .tableName(TABLE_NAME)
                .keyConditions(requestParam)
                .build()

            dynamoDbClient.query(request).run {
                if(!this.hasItems()){
                    return
                }
                this.items().forEach {
                    log.info("Item --> $it")
                }
            }
        }.onFailure {
            log.error("Failed to query item from DynamoDB -->", it)
            it.printStackTrace()
        }
    }

    private fun updateItem(pk:String, sk:String, balance:Int){
        kotlin.runCatching {
            val requestParam = mapOf(
                "PK" to AttributeValue.builder().s(pk).build(),
                "SK" to AttributeValue.builder().s(sk).build()
            )
            //방법1
//            val request = UpdateItemRequest.builder()
//                .tableName(TABLE_NAME)
//                .key(requestParam)
//                .updateExpression("set balance = :balance")
//                .expressionAttributeValues(mapOf(":balance" to AttributeValue.builder().n(balance.toString()).build()))
//                .build()

            //방법2
            val request = UpdateItemRequest.builder()
                .tableName(TABLE_NAME)
                .key(requestParam)
                .attributeUpdates(
                    mapOf(
                        "balance" to AttributeValueUpdate.builder()
                            .value(AttributeValue.builder().n(balance.toString()).build())
                            .action(AttributeAction.PUT)
                            .build()
                    )

                ).build()
            val response = dynamoDbClient.updateItem(request)
            response.attributes()?.forEach {
                log.info("Attribute --> Key: ${it.key}, value: ${it.value}")
            } ?: {
                log.info("Item was updated, but no attributes were returned.")
            }
        }.onFailure {
            log.error("Failed to update item from DynamoDB -->", it)
            it.printStackTrace()
        }
    }

    @QueryHandler
    fun query(command: QueryMoneySumByRegionQueryCommand): MoneySumByRegion {
        if(is_access_aws.not()){
            log.info("AWS에 연결되어 있지 않으므로 0 반환")
            return MoneySumByRegion.create(
                moneySumByRegionId = MoneySumByRegion.MoneySumByRegionId(UUID.randomUUID().toString()),
                regionName = MoneySumByRegion.RegionName(command.getAddress()),
                moneySum = MoneySumByRegion.MoneySum(0)
            )
        }
        return MoneySumByRegion.create(
            moneySumByRegionId = MoneySumByRegion.MoneySumByRegionId(UUID.randomUUID().toString()),
            regionName = MoneySumByRegion.RegionName(command.getAddress()),
            moneySum = MoneySumByRegion.MoneySum(getMoneySumByAddress(command.getAddress()))
        )
    }
}