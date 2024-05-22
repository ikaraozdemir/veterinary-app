package com.patika.cohort3.veterinaryapp.controller;

import com.patika.cohort3.veterinaryapp.dto.request.appointment.AppointmentSaveRequest;
import com.patika.cohort3.veterinaryapp.dto.request.appointment.AppointmentUpdateRequest;
import com.patika.cohort3.veterinaryapp.dto.response.appointment.AppointmentDateRangeResponse;
import com.patika.cohort3.veterinaryapp.dto.response.appointment.AppointmentResponse;
import com.patika.cohort3.veterinaryapp.entity.Appointment;
import com.patika.cohort3.veterinaryapp.mapper.AppointmentMapper;
import com.patika.cohort3.veterinaryapp.result.Result;
import com.patika.cohort3.veterinaryapp.result.ResultData;
import com.patika.cohort3.veterinaryapp.service.abstracts.AppointmentService;
import com.patika.cohort3.veterinaryapp.utilities.ResultHelper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1/appointments")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AppointmentResponse> save(@RequestBody AppointmentSaveRequest request) {
        Appointment saveAppointment = AppointmentMapper.INSTANCE.asEntity(request);
        this.appointmentService.save(saveAppointment);
        AppointmentResponse appointmentResponse = AppointmentMapper.INSTANCE.mapToResponse(saveAppointment);
        return ResultHelper.created(appointmentResponse);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> update(@Valid @RequestBody AppointmentUpdateRequest request) {
        Appointment updateAppointment = AppointmentMapper.INSTANCE.asEntity(request);
        this.appointmentService.update(updateAppointment);
        AppointmentResponse appointmentResponse = AppointmentMapper.INSTANCE.mapToResponse(updateAppointment);
        return ResultHelper.success(appointmentResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete (@PathVariable("id") Long id) {
        this.appointmentService.delete(id);
        return ResultHelper.ok();
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AppointmentResponse>> findAll() {
        List<Appointment> appointments = this.appointmentService.findAll();
        List<AppointmentResponse> responses = AppointmentMapper.INSTANCE.mapToResponse(appointments);
        return ResultHelper.success(responses);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> findById(@PathVariable("id") Long id) {
        Appointment appointment = this.appointmentService.getById(id);
        AppointmentResponse response = AppointmentMapper.INSTANCE.mapToResponse(appointment);
        return ResultHelper.success(response);
    }

    @GetMapping("doctors/{doctorId}/appointments-in-date-range")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AppointmentDateRangeResponse>> findAppointmentsByDoctorAndAppointmentsBetween (
            @PathVariable Long doctorId,
            @RequestParam("startDate")
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime startDate,
            @RequestParam("endDate")
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime endDate ) {
        List<Appointment> appointments = appointmentService.getAppointmentsByAppointmentDateBetweenAndDoctorId(doctorId, startDate, endDate);
        List<AppointmentDateRangeResponse> appointmentDateRangeResponses = AppointmentMapper.INSTANCE.mapToDateRangeResponse(appointments);
        return ResultHelper.success(appointmentDateRangeResponses);
    }

    @GetMapping("animals/{animalId}/appointments-in-date-range")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AppointmentDateRangeResponse>> findAppointmentsByAnimalAndAppointmentsBetween (
            @PathVariable Long animalId,
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime startDate,
            @RequestParam("endDate")
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime endDate ) {
        List<Appointment> appointments = appointmentService.getAppointmentsByAppointmentDateBetweenAndAnimalId(animalId, startDate, endDate);
        List<AppointmentDateRangeResponse> appointmentDateRangeResponses = AppointmentMapper.INSTANCE.mapToDateRangeResponse(appointments);
        return ResultHelper.success(appointmentDateRangeResponses);
    }



}
