package com.ms2709.banking.adapter.out.persistence;

import com.ms2709.banking.adapter.out.persistence.entity.FirmbankingRequestJpaEntity;
import com.ms2709.banking.domain.FirmbankingRequest;
import ms2709.global.Mapper;

import java.util.UUID;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 * @class RequestFirmbankingMapper
 * @since 2024-05-15 오전 11:40
 */
@Mapper
public class FirmbankingRequestMapper {
    public FirmbankingRequest mapToDomainEntity(FirmbankingRequestJpaEntity entity, UUID uuid){
        return FirmbankingRequest.create(
                new FirmbankingRequest.FirmbankingRequestId(entity.getRequestFirmbankingId() + ""),
                new FirmbankingRequest.FromBankName(entity.getFromBankName()),
                new FirmbankingRequest.FromBankAccountNumber(entity.getFromBankAccountNumber()),
                new FirmbankingRequest.ToBankName(entity.getToBankName()),
                new FirmbankingRequest.ToBankAccountNumber(entity.getToBankAccountNumber()),
                new FirmbankingRequest.MoneyAmount(entity.getMoneyAmount()),
                new FirmbankingRequest.FirmBankingStatus(entity.getFirmBankingStatus()),
                uuid
        );
    }
}
