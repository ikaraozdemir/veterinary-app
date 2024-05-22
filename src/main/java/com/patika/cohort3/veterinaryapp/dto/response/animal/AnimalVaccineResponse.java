package com.patika.cohort3.veterinaryapp.dto.response.animal;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AnimalVaccineResponse {
    private Long id;
    private String name;
    private String code;
    private LocalDate protectionStartDate;
    private LocalDate protectionFinishDate;
}
