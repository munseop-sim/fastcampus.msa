package ms2709.payservice.banking.application.port.out;

import ms2709.payservice.banking.adapter.out.persistence.entity.FirmbankingRequestJpaEntity;
import ms2709.payservice.banking.domain.FirmbankingRequest;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class RequestFirmbankingPort
 * @since 2024-05-15 오전 11:48
 */
public interface RequestFirmbankingPort {
    FirmbankingRequestJpaEntity createFirmbankingRequest(
            FirmbankingRequest.FromBankName fromBankName,
            FirmbankingRequest.FromBankAccountNumber fromBankAccountNumber,
            FirmbankingRequest.ToBankName toBankName,
            FirmbankingRequest.ToBankAccountNumber toBankAccountNumber,
            FirmbankingRequest.MoneyAmount moneyAmount,
            FirmbankingRequest.FirmBankingStatus firmBankingStatus,
            FirmbankingRequest.FirmbankingAggregateIdentifier aggregateIdentifier);

    FirmbankingRequestJpaEntity modifyFirmbankingRequest(FirmbankingRequestJpaEntity firmbankingRequestJpaEntity);

    FirmbankingRequestJpaEntity getFirmbankingRequest(
            FirmbankingRequest.FirmbankingAggregateIdentifier firmbankingAggregateIdentifier
    );
}
