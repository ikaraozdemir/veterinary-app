package com.patika.cohort3.veterinaryapp.dto.response.doctor;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DoctorAvailableDateResponse {
    private Long id;
    private LocalDate date;
}
