package com.patika.cohort3.veterinaryapp;

import com.patika.cohort3.veterinaryapp.dto.request.vaccine.VaccineAnimalRequest;
import com.patika.cohort3.veterinaryapp.dto.request.vaccine.VaccineSaveRequest;
import com.patika.cohort3.veterinaryapp.dto.request.vaccine.VaccineUpdateRequest;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VaccineDateRangeValidatorTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void saveRequest_WhenFinishDateIsBeforeStartDate_ShouldHaveViolation() {

        VaccineSaveRequest request = new VaccineSaveRequest();

        VaccineAnimalRequest animal = new VaccineAnimalRequest();
        request.setAnimal(animal);

        request.setProtectionStartDate(java.time.LocalDate.of(2024, 6, 1));
        request.setProtectionFinishDate(java.time.LocalDate.of(2024, 5, 31));

        var violations = validator.validate(request);

        boolean dateRuleViolationExists = violations.stream()
                .anyMatch(violation -> violation.getPropertyPath().toString().equals("protectionFinishDate"));
        assertTrue(dateRuleViolationExists, "Expected a violation on protectionFinishDate");

    }

    @Test
    void updateRequest_WhenFinishDateIsBeforeStartDate_ShouldHaveViolation() {

        VaccineUpdateRequest request = new VaccineUpdateRequest();
        request.setId(1L);

        VaccineAnimalRequest animal = new VaccineAnimalRequest();
        request.setAnimal(animal);

        request.setProtectionStartDate(java.time.LocalDate.of(2024, 6, 1));
        request.setProtectionFinishDate(java.time.LocalDate.of(2024, 5, 31));

        var violations = validator.validate(request);

        boolean dateRuleViolationExists = violations.stream()
                .anyMatch(violation -> violation.getPropertyPath().toString().equals("protectionFinishDate"));
        assertTrue(dateRuleViolationExists, "Expected a violation on protectionFinishDate");

    }

    @Test 
    void updateRequest_WhenFinishDateIsAfterStartDate_ShouldNotHaveViolation() {

        VaccineUpdateRequest request = new VaccineUpdateRequest();
        request.setId(1L);
        VaccineAnimalRequest animal = new VaccineAnimalRequest();
        request.setAnimal(animal);
        request.setProtectionStartDate(java.time.LocalDate.of(2024, 8, 21));
        request.setProtectionFinishDate(java.time.LocalDate.of(2024, 11, 15));

        var violations = validator.validate(request);

        boolean dateRuleViolationExists = violations.stream()
                .anyMatch(violation -> violation.getPropertyPath().toString().equals("protectionFinishDate"));
      
        assertFalse(dateRuleViolationExists, "Expected no violation on protectionFinishDate");

    }
}
