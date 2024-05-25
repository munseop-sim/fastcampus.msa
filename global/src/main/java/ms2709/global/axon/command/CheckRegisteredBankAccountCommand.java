package ms2709.global.axon.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckRegisteredBankAccountCommand {
    @TargetAggregateIdentifier
    private String aggregateIdentifier;
    private String rechargingRequestId;
    private String checkRegisteredBankAccountId;
    private String membershipId;
    private String bankName;
    private String bankAccountNumber;
    private int amount;
}