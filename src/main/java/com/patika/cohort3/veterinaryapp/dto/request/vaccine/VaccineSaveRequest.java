package com.patika.cohort3.veterinaryapp.dto.request.vaccine;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@ValidDateRange
public class VaccineSaveRequest implements ProtectionPeriod {
    private String name;
    private String code;
    @NotNull(message = "Protection start date cannot be null")
    private LocalDate protectionStartDate;
    @NotNull(message = "Protection finish date cannot be null")
    private LocalDate protectionFinishDate;
    @NotNull(message = "Animal cannot be null")
    @Valid
    private VaccineAnimalRequest animal;
}
