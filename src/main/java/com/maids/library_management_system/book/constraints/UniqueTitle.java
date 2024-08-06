package com.maids.library_management_system.book.constraints;

import com.maids.library_management_system.book.valdiators.UniqueTitleValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueTitleValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueTitle {

    String message() default "this title already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
