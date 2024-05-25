package com.patika.cohort3.veterinaryapp.dto.response.available.date;
import com.patika.cohort3.veterinaryapp.dto.response.doctor.DoctorSaveResponse;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AvailableDateResponse {
    private Long id;
    private LocalDate date;
    private DoctorSaveResponse doctor;
}
