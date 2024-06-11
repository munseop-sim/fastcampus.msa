package ms2709.global;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 클래스 설명
 *
 * @author 심문섭
 * @version 1.0
 *  LoggingAspect
 * @since 2024-05-19 오후 10:47
 */
@Aspect
@Component
public class LoggingAspect {
    private final LoggingProducer loggingProducer;

    public LoggingAspect(LoggingProducer loggingProducer) {
        this.loggingProducer = loggingProducer;
    }

    @Before("execution(* ms2709.payservice.*.adapter.in.web.*.*(..))")
    public void beforeMethodExecution(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();

        loggingProducer.sendMessage("logging", "Before executing method: " + methodName);
        // Produce Access log
    }
}