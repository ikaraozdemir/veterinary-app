package com.patika.cohort3.veterinaryapp.dto.request.available.date;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AvailableDateUpdateRequest {
    private Long id;
    private LocalDate date;
    private AvailableDateDoctorRequest doctor;
}
