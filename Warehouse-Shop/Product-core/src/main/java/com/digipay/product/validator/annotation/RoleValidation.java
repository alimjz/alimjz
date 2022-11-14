package com.digipay.product.validator.annotation;

import com.digipay.product.validator.RoleValidatorImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = RoleValidatorImpl.class)
public @interface RoleValidation {
    String role() default "";

    String message() default "Please Enter Correct Role";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
