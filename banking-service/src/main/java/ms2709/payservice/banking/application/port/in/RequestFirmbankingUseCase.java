package ms2709.payservice.banking.application.port.in;

import ms2709.payservice.banking.adapter.axon.command.UpdateRequestFirmbankingCommand;
import ms2709.payservice.banking.domain.FirmbankingRequest;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class RequestFirmbankingUseCase
 * @since 2024-05-15 오전 10:56
 */
public interface RequestFirmbankingUseCase {
    FirmbankingRequest requestFirmbanking(RequestFirmbankingCommand command);
    void requestFirmbankingByEvent(RequestFirmbankingCommand command);
    void updateFirmbankingByEvent(UpdateRequestFirmbankingCommand command);
}
