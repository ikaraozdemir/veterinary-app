package com.patika.cohort3.veterinaryapp.dto.request.available.date;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AvailableDateDoctorRequest {
    @NotNull(message = "Doctor id cannot be null")
    private Long id;
}
