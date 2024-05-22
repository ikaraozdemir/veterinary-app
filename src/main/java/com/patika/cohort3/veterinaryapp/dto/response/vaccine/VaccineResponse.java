package com.patika.cohort3.veterinaryapp.dto.response.vaccine;

import com.patika.cohort3.veterinaryapp.dto.request.vaccine.VaccineAnimalRequest;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
public class VaccineResponse {
    private Long id;
    private String name;
    private String code;
    private LocalDate protectionStartDate;
    private LocalDate protectionFinishDate;
    private VaccineAnimalResponse animal;
}
