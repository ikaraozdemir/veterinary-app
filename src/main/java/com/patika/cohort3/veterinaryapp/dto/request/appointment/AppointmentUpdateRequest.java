package com.patika.cohort3.veterinaryapp.dto.request.appointment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.patika.cohort3.veterinaryapp.dto.request.available.date.AvailableDateDoctorRequest;
import com.patika.cohort3.veterinaryapp.dto.request.vaccine.VaccineAnimalRequest;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentUpdateRequest {
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime appointmentDate;
    private AvailableDateDoctorRequest doctor;
    private VaccineAnimalRequest animal;
}
