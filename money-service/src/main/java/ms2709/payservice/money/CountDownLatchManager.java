package ms2709.payservice.money;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class CountDownLatchManager
 * @since 2024-05-21 오전 8:15
 */
@Slf4j
@Configuration
public class CountDownLatchManager {
    private final Map<String, CountDownLatch> countDownLatchMap;
    private final Map<String, String> stringMap;

    public CountDownLatchManager() {
        this.countDownLatchMap = new HashMap<>();
        this.stringMap = new HashMap<>();
    }

    public void addCountDownLatch(String key) {
        this.countDownLatchMap.put(key, new CountDownLatch(1));
        log.info("CountDownLatch added: {}", key);
    }

    public void setDataForKey(String key, String data){
        this.stringMap.put(key, data);
        log.info("Data added: {}", key);
    }
    public String getDataForKey(String key){
        log.info("Data get: {}", key);
        return this.stringMap.get(key);

    }
    public CountDownLatch getCountDownLatch(String key) {
        log.info("CountDownLatch get: {}", key);
        return this.countDownLatchMap.get(key);
    }
}