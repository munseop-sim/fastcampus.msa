package com.ms2709.banking.application.port.out;

import com.ms2709.banking.adapter.out.persistence.entity.FirmbankingRequestJpaEntity;
import com.ms2709.banking.domain.FirmbankingRequest;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class FindFirmbankingRequestPort
 * @since 2024-05-15 오후 1:44
 */
public interface FindFirmbankingRequestPort {
    FirmbankingRequestJpaEntity findFirmbankingRequest(FirmbankingRequest.FirmbankingRequestId firmbankingRequestId);
}
