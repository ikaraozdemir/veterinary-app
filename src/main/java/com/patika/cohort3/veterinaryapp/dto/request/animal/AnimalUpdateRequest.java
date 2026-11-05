package com.patika.cohort3.veterinaryapp.dto.request.animal;

import lombok.Data;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@Data
public class AnimalUpdateRequest {
    @NotNull (message = "Animal id cannot be null")
    private Long id;
    @Size (max = 100, message = "Animal name cannot exceed 100 characters")
    private String name;
    @NotBlank(message = "Animal species cannot be blank")
    @Size(max = 100, message = "Animal species cannot exceed 100 characters")
    private String species;
    private String gender;
    private String color;
    private String breed;
    @PastOrPresent(message = "Animal date of birth cannot be in the future")
    private LocalDate dateOfBirth;
    @NotNull(message = "Customer cannot be null")
    @Valid
    private AnimalCustomerRequest customer;
}
