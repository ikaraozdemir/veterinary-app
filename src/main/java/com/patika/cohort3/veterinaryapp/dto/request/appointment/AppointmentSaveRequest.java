package com.patika.cohort3.veterinaryapp.dto.request.appointment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.patika.cohort3.veterinaryapp.dto.request.available.date.AvailableDateDoctorRequest;
import com.patika.cohort3.veterinaryapp.dto.request.vaccine.VaccineAnimalRequest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentSaveRequest {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @NotNull(message = "Appointment date cannot be null")
    @Future(message = "Appointment date must be in the future")
    private LocalDateTime appointmentDate;
    @NotNull(message = "Doctor cannot be null")
    @Valid
    private AvailableDateDoctorRequest doctor;
    @NotNull(message = "Animal cannot be null")
    @Valid
    private VaccineAnimalRequest animal;
}
