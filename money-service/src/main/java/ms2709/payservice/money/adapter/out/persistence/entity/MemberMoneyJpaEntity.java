package ms2709.payservice.money.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member_money")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberMoneyJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberMoneyId;

    private Long membershipId;

    private int balance;

    private String aggregateIdentifier;

    public MemberMoneyJpaEntity(Long membershipId, int balance, String aggregateIdentifier) {
        this.membershipId = membershipId;
        this.balance = balance;
        this.aggregateIdentifier = aggregateIdentifier;
    }

    public Integer increaseMoney(int increaseMoneyAmount) {
        this.balance += increaseMoneyAmount;
        return this.balance;
    }
}
