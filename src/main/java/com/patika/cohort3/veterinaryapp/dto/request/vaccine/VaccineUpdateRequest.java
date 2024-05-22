package com.patika.cohort3.veterinaryapp.dto.request.vaccine;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
public class VaccineUpdateRequest {
    private Long id;
    private String name;
    @Column(unique = true, nullable = false)
    private String code;
    @Column(nullable = false)
    private LocalDate protectionStartDate;
    @Column(nullable = false)
    private LocalDate protectionFinishDate;
    private VaccineAnimalRequest animal;
}

