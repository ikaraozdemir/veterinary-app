package com.patika.cohort3.veterinaryapp.dto.response.vaccine;

import lombok.Data;

@Data
public class VaccineAnimalResponse {
    private Long id;
    private String name;
    private String gender;
}
