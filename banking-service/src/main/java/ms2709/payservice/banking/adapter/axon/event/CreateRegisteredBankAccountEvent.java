package ms2709.payservice.banking.adapter.axon.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateRegisteredBankAccountEvent {
    private String membershipId;

    private String bankName;
    private String bankAccountNumber;
}