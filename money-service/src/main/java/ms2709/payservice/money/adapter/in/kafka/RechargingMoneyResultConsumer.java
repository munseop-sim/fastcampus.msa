package ms2709.payservice.money.adapter.in.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import ms2709.global.LoggingProducer;
import ms2709.global.kafka.RechargingMoneyTask;
import ms2709.global.kafka.SubTask;
import ms2709.payservice.money.CountDownLatchManager;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.time.Duration;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class RechargingMoneyResultConsumer
 * @since 2024-05-21 오전 9:04
 */
@Slf4j
@Component
public class RechargingMoneyResultConsumer {
    private final KafkaConsumer<String, String> consumer;

    private final LoggingProducer loggingProducer;

    @NotNull
    private final CountDownLatchManager countDownLatchManager;

    private final ObjectMapper objectMapper;

    public RechargingMoneyResultConsumer(@Value("${kafka.clusters.bootstrapservers}") String bootstrapServers,
                                         @Value("${task.result.topic}")String topic,
                                         LoggingProducer loggingProducer,
                                         CountDownLatchManager countDownLatchManager,
                                         ObjectMapper objectMapper){
        this.loggingProducer = loggingProducer;
        this.countDownLatchManager = countDownLatchManager;
        this.objectMapper = objectMapper;
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("group.id", "my-group");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        this.consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList(topic));

        Thread consumerThread = new Thread(() -> {
            try {

                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
                    for (ConsumerRecord<String, String> record : records) {
                        System.out.println("Received message: " + record.key()  + " / "+  record.value());

//                        try {
//                            Thread.sleep(3000);
//                        } catch (InterruptedException e) {
//                            throw new RuntimeException(e);
//                        }
                        RechargingMoneyTask task;
                        log.info("record.value() : {}", record.value());
                        try {
                            task = this.objectMapper.readValue(record.value(), RechargingMoneyTask.class);
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }
                        log.info("Task received: {}, name:{}", task.getTaskID(), task.getTaskName());
                        List<SubTask> subTaskList = task.getSubTaskList();

                        boolean taskResult = true;
                        // validation membership
                        // validation banking
                        for (SubTask subTask : subTaskList) {
                            // 한번만 실패해도 실패한 task 로 간주.
                            if (subTask.getStatus().equals("fail")) {
                                taskResult = false;
                                break;
                            }
                        }
                        log.info("task result -> {}", taskResult);
                        // 모두 정상적으로 성공했다면
                        if (taskResult) {
                            this.loggingProducer.sendMessage(task.getTaskID(), "task success");
                            this.countDownLatchManager.setDataForKey(task.getTaskID(), "success");
                        } else{
                            this.loggingProducer.sendMessage(task.getTaskID(), "task failed");
                            this.countDownLatchManager.setDataForKey(task.getTaskID(), "failed");
                        }
                        var latch = this.countDownLatchManager.getCountDownLatch(RechargingMoneyTask.TASK_NAME);
                        if(latch != null){
                            latch.countDown();
                        }
                    }
                }
            } finally {
                consumer.close();
            }
        });
        consumerThread.start();
    }
}
