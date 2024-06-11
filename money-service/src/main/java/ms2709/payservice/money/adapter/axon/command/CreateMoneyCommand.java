package ms2709.payservice.money.adapter.axon.command;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ms2709.global.SelfValidating;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 *  CreateMoneyCommand
 * @since 2024-05-25 오후 1:43
 */




@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class CreateMoneyCommand  extends SelfValidating<CreateMoneyCommand> {
    @NotNull
    private String membershipId;

    public CreateMoneyCommand() {
        // Required by Axon to construct an empty instance to initiate Event Sourcing.
    }

    public CreateMoneyCommand(String membershipId) {
        this.membershipId = membershipId;
        this.validateSelf();
    }

    @Override
    public String toString() {
        return "CreateMoneyCommand{" +
                "membershipId='" + membershipId + '\'' +
                '}';
    }
}