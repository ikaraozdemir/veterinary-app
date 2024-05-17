package com.patika.cohort3.veterinaryapp.dto.response.customer;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CustomerAnimalResponse {
    private Long id;
    private String name;
    private String species;
    private String gender;
    private LocalDate dateOfBirth;
}
