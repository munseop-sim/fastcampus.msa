package ms2709.global;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Mapper의 역할을 나타내기 위한 어노테이션
 *
 * @author 심문섭
 * @version 1.0
 *  Mapper
 * @since 2024-05-02 오전 8:28
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Mapper {
    @AliasFor(annotation = Component.class)
    String value() default "";
}
