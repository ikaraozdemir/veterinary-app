package com.patika.cohort3.veterinaryapp.dto.request.available.date;
import lombok.Data;

import java.time.LocalDate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Data
public class AvailableDateUpdateRequest {
    @NotNull (message = "Available date id cannot be null")
    private Long id;
    @NotNull (message = "Available date cannot be null")
    private LocalDate date;
    @NotNull (message = "Doctor cannot be null")
    @Valid
    private AvailableDateDoctorRequest doctor;
}
