package ms2709.payservice.settlement.batch.scheduler

import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

/**
 *
 * 클래스 설명
 *
 * @class SettlementBatchScheduler
 * @author 심문섭
 * @version 1.0
 * @since 2024-06-18 오전 8:11
 */
@Component
class SettlementBatchScheduler (
    private val jobLauncher: JobLauncher,
    private val settlementJob: Job
){
    // @Scheduled(fixedRate = 1000)은 해당 메소드를 이전 실행 시작 시점으로부터 1000밀리초(1초)마다 실행하도록 스케줄링하라는 의미
    @Scheduled(fixedRate = 10000)
    fun runSettlementJob(){
        jobLauncher.run(settlementJob, JobParametersBuilder().apply {
            this.addLong("time", System.currentTimeMillis())
        }.toJobParameters())
    }
}