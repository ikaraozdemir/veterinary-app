package com.patika.cohort3.veterinaryapp.dto.request.available.date;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AvailableDateUpdateRequest {
    @Column(nullable = true)
    private Long id;
    private LocalDate date;
    private AvailableDateDoctorRequest doctor;
}
