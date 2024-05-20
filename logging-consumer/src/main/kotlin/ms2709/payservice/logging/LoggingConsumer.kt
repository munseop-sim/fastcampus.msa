package ms2709.payservice.logging

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.kafka.support.serializer.JsonDeserializer
import java.time.Duration
import java.util.*
import kotlin.collections.HashMap

//
//
/**
 *
 * 클래스 설명
 *
 * @class LoggingConsumer
 * @author 심문섭
 * @version 1.0
 * @since 2024-05-20 오전 8:32
 */
@Component
class LoggingConsumer(
    @Value("\${kafka.clusters.bootstrapservers}") bootstrapServers:String,
    @Value("\${logging.topic}") topic:String
) {
    private final var consumer:KafkaConsumer<String,String>

    init{
        val props = mapOf(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServers,
            ConsumerConfig.GROUP_ID_CONFIG to "my-group",
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java
        )

        this.consumer = KafkaConsumer(props)

        consumer.subscribe(listOf(topic))
        val consumerThread:Thread = Thread{
            runCatching {
                while(true){
                    val records:ConsumerRecords<String,String> = consumer.poll(Duration.ofSeconds(1L))
                    for(record:ConsumerRecord<String,String> in records){
                        println("Received message: ${record.value()}")
                    }
                }
            }.onFailure {
                consumer.close()
            }
        }
        consumerThread.start()

    }
}