package ms2709.global;


import jakarta.validation.*;
import java.util.Set;

/**
 *  Command객체의 유효성 검증을 위한 추상 클래스
 *
 * @author 심문섭
 * @version 1.0
 * @class SelfValidating
 * @since 2024-05-02 오전 8:24
 */
public abstract class SelfValidating<T> {

    private Validator validator;

    protected SelfValidating() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /**
     * Evaluates all Bean Validations on the attributes of this
     * instance.
     */
    protected void validateSelf() {
        Set<ConstraintViolation<T>> violations = validator.validate((T) this);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}