package ms2709.member.global

import org.springframework.web.bind.annotation.RestController
import java.lang.annotation.Documented

/**
 *
 * WebAdapter의 역할을 나타내기 위한 어노테이션
 *
 * @class WebAdapter
 * @author 심문섭
 * @version 1.0
 * @since 2024-04-29 8:58 AM
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@RestController
annotation class WebAdapter(val value: String = "") {

}