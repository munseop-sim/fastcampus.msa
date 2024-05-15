package com.ms2709.banking.application.port.out;

import com.ms2709.banking.adapter.out.persistence.entity.FirmbankingRequestJpaEntity;
import com.ms2709.banking.domain.FirmbankingRequest;

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
            FirmbankingRequest.FirmBankingStatus firmBankingStatus);

    FirmbankingRequestJpaEntity modifyFirmbankingRequest(FirmbankingRequestJpaEntity firmbankingRequestJpaEntity);
}
