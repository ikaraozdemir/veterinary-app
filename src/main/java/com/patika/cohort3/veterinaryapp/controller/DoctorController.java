package com.patika.cohort3.veterinaryapp.controller;

import com.patika.cohort3.veterinaryapp.dto.request.doctor.DoctorSaveRequest;
import com.patika.cohort3.veterinaryapp.dto.request.doctor.DoctorUpdateRequest;
import com.patika.cohort3.veterinaryapp.dto.response.customer.CustomerResponse;
import com.patika.cohort3.veterinaryapp.dto.response.doctor.DoctorResponse;
import com.patika.cohort3.veterinaryapp.dto.response.doctor.DoctorSaveResponse;
import com.patika.cohort3.veterinaryapp.entity.Customer;
import com.patika.cohort3.veterinaryapp.entity.Doctor;
import com.patika.cohort3.veterinaryapp.mapper.CustomerMapper;
import com.patika.cohort3.veterinaryapp.mapper.DoctorMapper;
import com.patika.cohort3.veterinaryapp.result.Result;
import com.patika.cohort3.veterinaryapp.result.ResultData;
import com.patika.cohort3.veterinaryapp.service.abstracts.DoctorService;
import com.patika.cohort3.veterinaryapp.utilities.ResultHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/doctors")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<DoctorSaveResponse> save(@RequestBody DoctorSaveRequest request) {
        Doctor saveDoctor = DoctorMapper.INSTANCE.asEntity(request);
        this.doctorService.save(saveDoctor);
        DoctorSaveResponse doctorSaveResponse = DoctorMapper.INSTANCE.mapToSaveResponse(saveDoctor);
        return ResultHelper.created(doctorSaveResponse);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<DoctorResponse> update(@RequestBody DoctorUpdateRequest request) {
        Doctor updateDoctor = DoctorMapper.INSTANCE.asEntity(request);
        this.doctorService.update(updateDoctor);
        DoctorResponse doctorResponse = DoctorMapper.INSTANCE.mapToResponse(updateDoctor);
        return ResultHelper.success(doctorResponse);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<DoctorResponse>> findAll() {
        List<Doctor> doctors = this.doctorService.findAll();
        List<DoctorResponse> responses = DoctorMapper.INSTANCE.mapToResponse(doctors);
        return ResultHelper.success(responses);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete (@PathVariable("id") Long id) {
        this.doctorService.delete(id);
        return ResultHelper.ok();
    }

}
