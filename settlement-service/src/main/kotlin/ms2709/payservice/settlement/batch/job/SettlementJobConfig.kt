package ms2709.payservice.settlement.batch.job

import ms2709.payservice.settlement.batch.tasklet.SettlementTasklet
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.orm.jpa.JpaTransactionManager

/**
 *
 * 클래스 설명
 *
 * @class SettlementJobConfig
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-18 오전 8:13
 */
@Configuration
class SettlementJobConfig(
//    private val jobBuilderFactory: JobBuilderFactory,
//    private val stepBuilderFactory: StepBuilderFactory,
    private val jobRepository: JobRepository,
    private val jpaTransactionManager: JpaTransactionManager,
    private val settlementTasklet: SettlementTasklet
) {

    @Bean
    fun settlementJob(): Job {
        return org.springframework.batch.core.job.builder.JobBuilder("settlementJob", jobRepository)
            .start(settlementStep())
            .build()

    }

    fun settlementStep(): Step {
        return StepBuilder("settlementStep", jobRepository)
            .tasklet(settlementTasklet, jpaTransactionManager)
            .build()

    }
}