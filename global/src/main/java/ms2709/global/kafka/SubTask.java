package ms2709.global.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 *  SubTask
 * @since 2024-05-21 오전 8:10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubTask {
    private String membershipID;
    private String subTaskName;
    private TaskType taskType; // "banking", "membership"
    private Status status; // "success", "fail", "ready"

    public enum TaskType {
        BANKING, MEMBERSHIP
    }

    public enum Status {
        SUCCESS, FAIL, READY
    }

}