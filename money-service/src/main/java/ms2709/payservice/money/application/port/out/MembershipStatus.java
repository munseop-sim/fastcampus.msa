package ms2709.payservice.money.application.port.out;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 *  MembershipStatus
 * @since 2024-05-25 오후 2:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MembershipStatus {
    private String membershipId;
    private boolean isValid;
}