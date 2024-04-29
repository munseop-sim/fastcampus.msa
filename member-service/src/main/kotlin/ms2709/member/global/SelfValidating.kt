package ms2709.member.global

import jakarta.validation.ConstraintViolationException
import jakarta.validation.Validation
import jakarta.validation.Validator

/**
 *
 * Command객체의 유효성 검증을 위한 추상 클래스
 *
 * @class SelfValidating
 * @author 심문섭
 * @version 1.0
 * @since 2024-04-29 9:51PM
 */
abstract class SelfValidating<T> {
    private val validator: Validator
    constructor() {
        Validation.buildDefaultValidatorFactory().run {
            this@SelfValidating.validator = this.validator
        }
    }

    protected fun validateSelf() {
        val violations = validator.validate(this)
        if (violations.isNotEmpty()) {
            throw ConstraintViolationException(violations)
        }
    }
}