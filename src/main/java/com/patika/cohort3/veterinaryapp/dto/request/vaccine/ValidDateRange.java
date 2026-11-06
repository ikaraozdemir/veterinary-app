package com.patika.cohort3.veterinaryapp.dto.request.vaccine;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = VaccineDateRangeValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDateRange {
    String message() default "Protection finish date must be after the start date";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}