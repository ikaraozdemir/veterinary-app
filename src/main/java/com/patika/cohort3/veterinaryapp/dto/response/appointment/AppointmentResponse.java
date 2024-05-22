package com.patika.cohort3.veterinaryapp.dto.response.appointment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.patika.cohort3.veterinaryapp.dto.response.vaccine.VaccineAnimalResponse;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentResponse {
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime appointmentDate;
    private AppointmentDoctorResponse doctor;
    private VaccineAnimalResponse animal;
}
