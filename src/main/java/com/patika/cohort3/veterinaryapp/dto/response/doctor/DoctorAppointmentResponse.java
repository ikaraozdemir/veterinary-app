package com.patika.cohort3.veterinaryapp.dto.response.doctor;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DoctorAppointmentResponse {
    private Long id;
    private LocalDateTime appointmentDate;
}
