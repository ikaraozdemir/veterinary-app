package com.patika.cohort3.veterinaryapp.dto.request.vaccine;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VaccineAnimalRequest {
    @NotNull(message = "Animal id cannot be null")
    private Long id;
}
