package ms2709.payservice.banking.application.port.out;

import ms2709.payservice.banking.adapter.out.persistence.entity.FirmbankingRequestJpaEntity;
import ms2709.payservice.banking.domain.FirmbankingRequest;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 *  FindFirmbankingRequestPort
 * @since 2024-05-15 오후 1:44
 */
public interface FindFirmbankingRequestPort {
    FirmbankingRequestJpaEntity findFirmbankingRequest(FirmbankingRequest.FirmbankingRequestId firmbankingRequestId);
}
