package ms2709.payservice.banking.application.port.out;

import ms2709.payservice.banking.adapter.out.external.bank.ExternalFirmbankingRequest;
import ms2709.payservice.banking.adapter.out.external.bank.FirmbankingResult;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class RequestExternalFirmbankingPort
 * @since 2024-05-15 오전 11:53
 */
public interface RequestExternalFirmbankingPort {
    FirmbankingResult requestExternalFirmbanking(ExternalFirmbankingRequest firmbankingRequest);
}
