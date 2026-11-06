package com.patika.cohort3.veterinaryapp.dto.request.vaccine;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class VaccineDateRangeValidator implements ConstraintValidator<ValidDateRange, ProtectionPeriod> {
    @Override
    public boolean isValid(ProtectionPeriod request, ConstraintValidatorContext context) {
        if (request == null) {
            return true;
        }

        LocalDate startDate = request.getProtectionStartDate();
        LocalDate finishDate = request.getProtectionFinishDate();
        if (startDate == null || finishDate == null) {
            return true;
        }

        if (finishDate.isAfter(startDate)) {
            return true;
        }

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                .addPropertyNode("protectionFinishDate")
                .addConstraintViolation();
        return false;
    }
}