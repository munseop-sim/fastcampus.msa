package ms2709.payservice.taskconsumer

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.clients.producer.RecordMetadata
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*


/**
 *
 * 클래스 설명
 *
 *  TaskResultProducer
 * @author 심문섭
 * @version 1.0
 * @since 2024-05-21 오전 8:46
 */
@Component
class TaskResultProducer(
    @Value("\${kafka.clusters.bootstrapservers}") private val bootstrapServers:String,
    @Value("\${task.result.topic}") private val topic:String,
    private val objectMapper: ObjectMapper
) {
    private val producer: KafkaProducer<String, String>
    init {
        val props = Properties()
        props[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
        props[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        this.producer = KafkaProducer<String,String>(props);
    }

    fun sendTaskResult(key: String, task: Any?) {
        val jsonStringToProduce: String
        try {
            jsonStringToProduce = objectMapper.writeValueAsString(task)
        } catch (e: JsonProcessingException) {
            throw RuntimeException(e)
        }
        val record = ProducerRecord(topic, key, jsonStringToProduce)
        producer.send(record) { _: RecordMetadata?, exception: Exception? ->
            if (exception == null) {
                // System.out.println("Message sent successfully. Offset: " + metadata.offset());
            } else {
                exception.printStackTrace()
                // System.err.println("Failed to send message: " + exception.getMessage());
            }
        }
    }
}