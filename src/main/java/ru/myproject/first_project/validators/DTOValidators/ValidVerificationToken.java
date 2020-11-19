package ru.myproject.first_project.validators.DTOValidators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidatorVerificationToken.class)
public @interface ValidVerificationToken {
    String message() default "Invalid or expired token";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
