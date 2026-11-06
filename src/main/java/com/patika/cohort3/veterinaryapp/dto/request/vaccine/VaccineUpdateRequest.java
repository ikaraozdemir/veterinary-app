package com.patika.cohort3.veterinaryapp.dto.request.vaccine;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@ValidDateRange
public class VaccineUpdateRequest implements ProtectionPeriod {
    @NotNull(message = "Vaccine id cannot be null")
    private Long id;
    private String name;
    private String code;
    @NotNull (message = "Protection start date cannot be null")
    private LocalDate protectionStartDate;
    @NotNull (message = "Protection finish date cannot be null")
    private LocalDate protectionFinishDate;
    @Valid 
    @NotNull (message = "Animal cannot be null")
    private VaccineAnimalRequest animal;
}

