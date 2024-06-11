package ms2709.payservice.money.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms2709.global.UseCase;
import ms2709.payservice.money.adapter.out.persistence.MemberMoneyMapper;
import ms2709.payservice.money.application.port.in.FindMemberMoneyListByMembershipIdListCommand;
import ms2709.payservice.money.application.port.in.FindMoneyUseCase;
import ms2709.payservice.money.application.port.out.GetMemberMoneyPort;
import ms2709.payservice.money.domain.MemberMoney;

import java.util.List;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class FindMoneyService
 * @since 2024-06-11 오전 10:48
 */
@UseCase
@RequiredArgsConstructor
@Slf4j
public class FindMoneyService implements FindMoneyUseCase {
    private final GetMemberMoneyPort getMemberMoneyPort;
    private final MemberMoneyMapper memberMoneyMapper;

    @Override
    public List<MemberMoney> findMemberMoneyListByMembershipList(FindMemberMoneyListByMembershipIdListCommand command) {
        var idList = command.membershipIdList().stream().map(t->new MemberMoney.MembershipId(t)).toList();
        var memberMoneyEntityList = getMemberMoneyPort.findMemberMoneyByMembershipIdList(idList);
        return memberMoneyEntityList.stream().map(memberMoneyMapper::mapToEntity).toList();
    }
}
