package ms2709.payservice.remittance.adapter.out.persistence

import ms2709.payservice.remittance.adapter.out.persistence.entity.RemittanceRequestJpaEntity
import ms2709.payservice.remittance.domain.RemittanceRequest
import org.springframework.stereotype.Component

/**
 *
 * 클래스 설명
 *
 *  RemittanceRequestMapper
 * @author 심문섭
 * @version 1.0
 * @since 2024-05-25 오전 6:58
 */
@Component
class RemittanceRequestMapper {
    fun mapToEntity(entity: RemittanceRequestJpaEntity): RemittanceRequest{
        return RemittanceRequest.create(
            remittanceRequestId = RemittanceRequest.RemittanceRequestId(entity.remittanceRequestId!!.toString()),
            remittanceFromMembershipId = RemittanceRequest.RemittanceFromMembershipId(entity.fromMembershipId!!),
            toBankName = RemittanceRequest.ToBankName(entity.toBankName!!),
            toBankAccountNumber = RemittanceRequest.ToBankAccountNumber(entity.toBankAccountNumber!!),
            remittanceType = RemittanceRequest.RemittanceType(entity.remittanceType!!),
            amount = RemittanceRequest.Amount(entity.amount!!),
            remittanceStatus = RemittanceRequest.RemittanceStatus(entity.remittanceStatus!!)
        )
    }
}