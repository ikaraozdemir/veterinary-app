package com.patika.cohort3.veterinaryapp.dto.response.animal;

import lombok.Data;

import java.util.List;

@Data
public class AnimalGetVaccinesResponse {
    private String name;
    private List<AnimalVaccineResponse> vaccines;
}
