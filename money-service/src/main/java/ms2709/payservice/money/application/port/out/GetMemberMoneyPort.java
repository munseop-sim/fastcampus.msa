package ms2709.payservice.money.application.port.out;

import ms2709.payservice.money.adapter.out.persistence.entity.MemberMoneyJpaEntity;
import ms2709.payservice.money.domain.MemberMoney;

import java.util.List;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class GetMemberMoneyPort
 * @since 2024-05-25 오후 2:20
 */
public interface GetMemberMoneyPort {
    MemberMoneyJpaEntity getMemberMoney(
            MemberMoney.MembershipId memberId
    );
    List<MemberMoneyJpaEntity> findMemberMoneyByMembershipIdList(List<MemberMoney.MembershipId> membershipIdList);
}
