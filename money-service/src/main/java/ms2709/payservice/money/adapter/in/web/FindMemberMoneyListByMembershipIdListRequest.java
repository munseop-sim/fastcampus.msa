package ms2709.payservice.money.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class FindMemberMoneyListByMembershipIdListRequest
 * @since 2024-06-11 오전 10:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindMemberMoneyListByMembershipIdListRequest {
    private List<String> membershipIdList;
}
