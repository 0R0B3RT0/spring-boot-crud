package com.spring.springbootcrud.domain.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = IsValidCPFValidator.class)
public @interface IsValidCPF {

  String message() default "Valor inválido";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  String value() default "";
}
