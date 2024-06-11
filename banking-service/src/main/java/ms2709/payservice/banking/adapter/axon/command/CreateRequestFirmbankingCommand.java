package ms2709.payservice.banking.adapter.axon.command;

import lombok.*;
import ms2709.global.SelfValidating;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 *  CreateRequestFirmbankingCommand
 * @since 2024-05-25 오후 3:06
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRequestFirmbankingCommand {
    private String fromBankName;

    private String fromBankAccountNumber;

    private String toBankName;

    private String toBankAccountNumber;

    private int moneyAmount;

}