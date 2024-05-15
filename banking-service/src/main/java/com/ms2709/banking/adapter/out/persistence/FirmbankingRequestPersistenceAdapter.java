package com.ms2709.banking.adapter.out.persistence;

import com.ms2709.banking.adapter.out.persistence.entity.FirmbankingRequestJpaEntity;
import com.ms2709.banking.adapter.out.persistence.repository.FirmbankingRequestJpaEntityRepository;
import com.ms2709.banking.application.port.out.FindFirmbankingRequestPort;
import com.ms2709.banking.application.port.out.RequestFirmbankingPort;
import com.ms2709.banking.domain.FirmbankingRequest;
import lombok.RequiredArgsConstructor;
import ms2709.global.PersistenceAdapter;

import java.util.UUID;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class FirmbankingRequestPersistanceAdapter
 * @since 2024-05-15 오후 12:02
 */
@PersistenceAdapter
@RequiredArgsConstructor
public class FirmbankingRequestPersistenceAdapter implements RequestFirmbankingPort, FindFirmbankingRequestPort {

    private final FirmbankingRequestJpaEntityRepository repository;

    @Override
    public FirmbankingRequestJpaEntity createFirmbankingRequest(FirmbankingRequest.FromBankName fromBankName, FirmbankingRequest.FromBankAccountNumber fromBankAccountNumber, FirmbankingRequest.ToBankName toBankName, FirmbankingRequest.ToBankAccountNumber toBankAccountNumber, FirmbankingRequest.MoneyAmount moneyAmount, FirmbankingRequest.FirmBankingStatus firmBankingStatus) {
        return repository.save(
                new FirmbankingRequestJpaEntity(
                        fromBankName.value(),
                        fromBankAccountNumber.value(),
                        toBankName.value(),
                        toBankAccountNumber.value(),
                        moneyAmount.value(),
                        firmBankingStatus.value(),
                        UUID.randomUUID().toString()
                )
        );
    }

    @Override
    public FirmbankingRequestJpaEntity modifyFirmbankingRequest(FirmbankingRequestJpaEntity firmbankingRequestJpaEntity) {
        return repository.save(firmbankingRequestJpaEntity);
    }

    @Override
    public FirmbankingRequestJpaEntity findFirmbankingRequest(FirmbankingRequest.FirmbankingRequestId firmbankingRequestId) {
        var id = Long.parseLong(firmbankingRequestId.value());
        return repository.findById(id).orElse(null);
    }
}
