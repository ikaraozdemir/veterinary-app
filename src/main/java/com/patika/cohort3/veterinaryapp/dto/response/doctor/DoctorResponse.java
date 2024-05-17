package com.patika.cohort3.veterinaryapp.dto.response.doctor;

import lombok.Data;

import java.util.List;
@Data
public class DoctorResponse {
    private Long id;
    private String name;
    private String mpNo;
    private String mail;
    private List<DoctorAppointmentResponse> appointments;
    private List<DoctorAvailableDateResponse> availableDates;
}
