package ms2709.payservice.taskconsumer

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import ms2709.global.kafka.RechargingMoneyTask
import ms2709.global.kafka.SubTask
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.ConsumerRecords
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.Duration
import java.util.*


/**
 *
 * 클래스 설명
 *
 * @class TaskConsumer
 * @author 심문섭
 * @version 1.0
 * @since 2024-05-21 오전 8:45
 */
@Component
class TaskConsumer(
    @Value("\${kafka.clusters.bootstrapservers}") private val bootstrapServers: String,
    @Value("\${task.topic}") private val topic: String,
    private val taskResultProducer: TaskResultProducer,
    private val objectMapper: ObjectMapper
) {
    private val consumer: KafkaConsumer<String, String>

    init {
        Properties().apply {
            this[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
            this[ConsumerConfig.GROUP_ID_CONFIG] = "my-group"
            this[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = "org.apache.kafka.common.serialization.StringDeserializer"
            this[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = "org.apache.kafka.common.serialization.StringDeserializer"
        }.run {
            this@TaskConsumer.consumer = KafkaConsumer(this)
            this@TaskConsumer.consumer
        }.also {
            it.subscribe(listOf(topic))
        }

        this.consumeThread()
    }

    fun consumeThread(){
        val consumerThread = Thread {
            consumer.use { consumer ->
                while (true) {

                    val records: ConsumerRecords<String, String> = consumer.poll(Duration.ofSeconds(1))
                    for (record in records) {
                        println("Received message: " + record.value())

                        try {
                            val tasks = objectMapper.readValue(
                                record.value(),
                                RechargingMoneyTask::class.java
                            )
                            val subTaskList = tasks.subTaskList

                            // validation membership
                            // validation banking
                            for (subTask in subTaskList) {
                                subTask.status = SubTask.Status.SUCCESS
                            }

                            taskResultProducer.sendTaskResult(tasks.taskID, tasks)
                        } catch (e: JsonProcessingException) {
                            throw RuntimeException(e)
                        }
                    }
                }
            }
        }
        consumerThread.start()
    }

}