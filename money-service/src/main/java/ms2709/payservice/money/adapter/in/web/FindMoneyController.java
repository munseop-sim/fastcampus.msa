package ms2709.payservice.money.adapter.in.web;

import lombok.RequiredArgsConstructor;
import ms2709.global.WebAdapter;
import ms2709.payservice.money.application.port.in.FindMemberMoneyListByMembershipIdListCommand;
import ms2709.payservice.money.application.port.in.FindMoneyUseCase;
import ms2709.payservice.money.domain.MemberMoney;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 *  FindMoneyController
 * @since 2024-06-11 오전 10:43
 */
@WebAdapter
@RestController
@RequestMapping("/money")
@RequiredArgsConstructor
public class FindMoneyController {
    private final FindMoneyUseCase findMoneyUseCase;
    @PostMapping(path = "/find/member-money")
    List<MemberMoney> findMemberMoneyListByMembershipIds(@RequestBody FindMemberMoneyListByMembershipIdListRequest request) {
        FindMemberMoneyListByMembershipIdListCommand command = new FindMemberMoneyListByMembershipIdListCommand(request.getMembershipIdList());
        return findMoneyUseCase.findMemberMoneyListByMembershipList(command);
    }
}
