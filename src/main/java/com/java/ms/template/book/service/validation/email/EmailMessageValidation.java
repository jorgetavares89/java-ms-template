package com.java.ms.template.book.service.validation.email;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailMessageValidator.class)
public @interface EmailMessageValidation {

    String message() default "Email message is not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}