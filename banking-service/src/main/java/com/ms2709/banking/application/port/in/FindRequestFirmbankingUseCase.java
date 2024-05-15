package com.ms2709.banking.application.port.in;

import com.ms2709.banking.domain.FirmbankingRequest;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class FindRequestFirmbakingUseCase
 * @since 2024-05-15 오후 1:37
 */
public interface FindRequestFirmbankingUseCase {
    FirmbankingRequest findRequestFirmbanking(FindRequestFirmbankingCommand command);

}
