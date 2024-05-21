package ms2709.payservice.money.adapter.out.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import ms2709.global.kafka.RechargingMoneyTask;
import ms2709.payservice.money.application.port.out.SendRechargingMoneyTaskPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import java.util.Properties;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class TaskProducer
 * @since 2024-05-21 오전 8:24
 */
@Slf4j
@Component
public class TaskProducer implements SendRechargingMoneyTaskPort {
    private final KafkaProducer<String, String> producer;
    private final String topic;
    private final ObjectMapper objectMapper;
    public TaskProducer(@Value("${kafka.clusters.bootstrapservers}") String bootstrapServers,
                        @Value("${task.topic}")String topic,
                        ObjectMapper objectMapper) {

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        this.producer = new KafkaProducer<>(props);
        this.topic = topic;
        this.objectMapper = objectMapper;
    }

    public void sendTask (String key, RechargingMoneyTask task) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStringToProduce;
        try {
            jsonStringToProduce = objectMapper.writeValueAsString(task);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, jsonStringToProduce);
        producer.send(record, (metadata, exception) -> {
            log.info("pruduced message: {} to topic: {} with key: {}", jsonStringToProduce, topic, key);
            if (exception == null) {
                // System.out.println("Message sent successfully. Offset: " + metadata.offset());
            } else {
                exception.printStackTrace();
                // System.err.println("Failed to send message: " + exception.getMessage());
            }
        });
    }

    @Override
    public void sendRechargingMoneyTaskPort(RechargingMoneyTask task) {
        this.sendTask(task.getTaskID(), task);
    }
}
