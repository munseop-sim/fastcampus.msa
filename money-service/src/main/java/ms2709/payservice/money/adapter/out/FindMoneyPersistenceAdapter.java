package ms2709.payservice.money.adapter.out;

import lombok.RequiredArgsConstructor;
import ms2709.global.PersistenceAdapter;
import ms2709.payservice.money.adapter.out.persistence.entity.MemberMoneyJpaEntity;
import ms2709.payservice.money.adapter.out.persistence.repository.MemberMoneyJpaEntityRepository;
import ms2709.payservice.money.application.port.out.GetMemberMoneyPort;
import ms2709.payservice.money.domain.MemberMoney;

import java.util.List;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class FindMoneyPersistenceAdapter
 * @since 2024-06-11 오전 10:52
 */
@RequiredArgsConstructor
@PersistenceAdapter
public class FindMoneyPersistenceAdapter implements GetMemberMoneyPort {
    private final MemberMoneyJpaEntityRepository memberMoneyJpaEntityRepository;


    @Override
    public MemberMoneyJpaEntity getMemberMoney(MemberMoney.MembershipId memberId) {
        List<MemberMoneyJpaEntity> entityList =  memberMoneyJpaEntityRepository.findByMembershipId(Long.parseLong(memberId.value()));
        //todo - 예외처리
        return entityList.get(0);


    }

    @Override
    public List<MemberMoneyJpaEntity> findMemberMoneyByMembershipIdList(List<MemberMoney.MembershipId> membershipIdList) {
        var idList = membershipIdList.stream().map(id -> Long.parseLong(id.value())).toList();
        return memberMoneyJpaEntityRepository.findMemberMoneyListByMembershipIds(idList);
    }
}
