package com.patika.cohort3.veterinaryapp.dto.request.vaccine;
import lombok.Data;

import java.time.LocalDate;

@Data
public class VaccineUpdateRequest {
    private Long id;
    private String name;
    private String code;
    private LocalDate protectionStartDate;
    private LocalDate protectionFinishDate;
    private VaccineAnimalRequest animal;
}

