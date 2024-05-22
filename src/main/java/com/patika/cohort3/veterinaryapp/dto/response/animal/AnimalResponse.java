package com.patika.cohort3.veterinaryapp.dto.response.animal;

import com.patika.cohort3.veterinaryapp.dto.response.customer.CustomerSaveResponse;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AnimalResponse {
    private Long id;
    private String name;
    private String species;
    private String gender;
    private String color;
    private String breed;
    private LocalDate dateOfBirth;
    private CustomerSaveResponse customer;
}
