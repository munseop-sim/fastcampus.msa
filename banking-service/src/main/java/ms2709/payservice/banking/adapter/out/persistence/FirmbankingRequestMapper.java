package ms2709.payservice.banking.adapter.out.persistence;

import ms2709.payservice.banking.adapter.out.persistence.entity.FirmbankingRequestJpaEntity;
import ms2709.payservice.banking.domain.FirmbankingRequest;
import ms2709.global.Mapper;

import java.util.UUID;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 *  RequestFirmbankingMapper
 * @since 2024-05-15 오전 11:40
 */
@Mapper
public class FirmbankingRequestMapper {
    public FirmbankingRequest mapToDomainEntity(FirmbankingRequestJpaEntity entity, UUID uuid,String aggregateIdentifier){
        return FirmbankingRequest.create(
                new FirmbankingRequest.FirmbankingRequestId(entity.getRequestFirmbankingId() + ""),
                new FirmbankingRequest.FromBankName(entity.getFromBankName()),
                new FirmbankingRequest.FromBankAccountNumber(entity.getFromBankAccountNumber()),
                new FirmbankingRequest.ToBankName(entity.getToBankName()),
                new FirmbankingRequest.ToBankAccountNumber(entity.getToBankAccountNumber()),
                new FirmbankingRequest.MoneyAmount(entity.getMoneyAmount()),
                new FirmbankingRequest.FirmBankingStatus(entity.getFirmBankingStatus()),
                uuid,
                new FirmbankingRequest.FirmbankingAggregateIdentifier(aggregateIdentifier)
        );
    }
}
