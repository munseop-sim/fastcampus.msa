package ms2709.member.global

import org.springframework.stereotype.Component
import java.lang.annotation.Documented

/**
 *
 * UseCase의 역할을 나타내기 위한 어노테이션
 *
 * @class UseCase
 * @author 심문섭
 * @version 1.0
 * @since 2024-04-29 8:58 AM
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Component
annotation class UseCase(val value: String = "") {

}