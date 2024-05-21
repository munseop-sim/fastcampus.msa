package ms2709.global.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ms2709.global.BankTypes;

import java.util.List;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class RechargingMoneyTask
 * @since 2024-05-21 오전 8:12
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RechargingMoneyTask implements KafkaTask { // Increase Money

    public static final String TASK_NAME = "RechargingMoneyTask";

    private String taskID;
    private final String taskName = TASK_NAME;

    private String membershipID;

    private List<SubTask> subTaskList;

    // 법인 계좌
    private BankTypes toBankName;

    // 법인 계좌 번호
    private String toBankAccountNumber;

    private int moneyAmount; // only won
}