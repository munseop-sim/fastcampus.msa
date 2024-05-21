package ms2709.payservice.banking.adapter.out.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Banking Service 에서 사용하는 Membership 정보를 담는 클래스
 *
 * @author 심문섭
 * @version 1.0
 * @class Membership
 * @since 2024-05-20 오후 9:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Membership {
    private String membershipId;
    private String name;
    private String email;
    private String address;
    private boolean isValid;
    private boolean isCorp;
}
