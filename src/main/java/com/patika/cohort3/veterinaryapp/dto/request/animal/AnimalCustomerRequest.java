package com.patika.cohort3.veterinaryapp.dto.request.animal;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AnimalCustomerRequest {
    @NotNull(message = "Customer id cannot be null")
    private Long id;
}
