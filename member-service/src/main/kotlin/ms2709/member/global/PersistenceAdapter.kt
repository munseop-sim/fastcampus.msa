package ms2709.member.global

import org.springframework.stereotype.Component
import java.lang.annotation.Documented

/**
 *
 * PersistenceAdapter의 역할을 나타내기 위한 어노테이션
 *
 * @class PersistenceAdapter
 * @author 심문섭
 * @version 1.0
 * @since 2024-04-29 9:20 AM
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Component
annotation class PersistenceAdapter (val value: String = ""){
}