package ms2709.global.axon.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckRegisteredBankAccountEvent {
    private String membershipId;
}