package ms2709.payservice.remittance.adapter.out.persistence

import ms2709.global.PersistenceAdapter
import ms2709.payservice.remittance.adapter.out.persistence.entity.RemittanceRequestJpaEntity
import ms2709.payservice.remittance.adapter.out.persistence.repository.RemittanceRequestJpaEntityRepository
import ms2709.payservice.remittance.application.port.`in`.FindRemittanceCommand
import ms2709.payservice.remittance.application.port.`in`.RequestRemittanceCommand
import ms2709.payservice.remittance.application.port.out.FindRemittancePort
import ms2709.payservice.remittance.application.port.out.RequestRemittancePort
import org.springframework.data.jpa.repository.JpaRepository

/**
 *
 * 클래스 설명
 *
 *  RemittanceRequestPersistenceAdapter
 * @author 심문섭
 * @version 1.0
 * @since 2024-05-25 오전 7:34
 */
@PersistenceAdapter
class RemittanceRequestPersistenceAdapter (
    private val repository: RemittanceRequestJpaEntityRepository
): RequestRemittancePort, FindRemittancePort {
    override fun createRemittanceRequestHistory(command: RequestRemittanceCommand): RemittanceRequestJpaEntity {
        return RemittanceRequestJpaEntity(
            fromMembershipId = command.fromMembershipId,
            toMembershipId = command.toMembershipId,
            toBankName = command.toBankName,
            toBankAccountNumber = command.toBankAccountNumber,
            remittanceType = command.remittanceType,
            amount = command.amount
        ).run {
            repository.save(this)
        }
    }

    override fun saveRemittanceRequestHistory(entity: RemittanceRequestJpaEntity): Boolean {
        repository.save(entity);
        return true;
    }

    override fun findRemittanceHistory(command: FindRemittanceCommand): List<RemittanceRequestJpaEntity> {
        return repository.findByFromMembershipId(command.getMembershipId())
    }
}