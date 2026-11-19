package com.patika.cohort3.veterinaryapp.mapper;

import com.patika.cohort3.veterinaryapp.dto.request.appointment.AppointmentSaveRequest;
import com.patika.cohort3.veterinaryapp.dto.request.appointment.AppointmentUpdateRequest;
import com.patika.cohort3.veterinaryapp.dto.response.appointment.AppointmentDateRangeResponse;
import com.patika.cohort3.veterinaryapp.dto.response.appointment.AppointmentResponse;
import com.patika.cohort3.veterinaryapp.entity.Appointment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
(componentModel = "spring")
public interface AppointmentMapper {
    Appointment asEntity(AppointmentSaveRequest appointmentSaveRequest);
    Appointment asEntity(AppointmentUpdateRequest appointmentUpdateRequest);
    AppointmentResponse mapToResponse(Appointment appointment);
    List<AppointmentDateRangeResponse> mapToDateRangeResponse(List<Appointment> appointments);
    List<AppointmentResponse> mapToResponse(List<Appointment> appointments);
}
