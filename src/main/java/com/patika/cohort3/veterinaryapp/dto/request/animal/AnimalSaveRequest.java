package com.patika.cohort3.veterinaryapp.dto.request.animal;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AnimalSaveRequest {
    private String name;
    private String species;
    private String gender;
    private String color;
    private String breed;
    private LocalDate dateOfBirth;
    private AnimalCustomerRequest customer;
}
