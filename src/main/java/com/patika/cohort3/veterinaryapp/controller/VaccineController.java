package com.patika.cohort3.veterinaryapp.controller;
import com.patika.cohort3.veterinaryapp.dto.request.vaccine.VaccineSaveRequest;
import com.patika.cohort3.veterinaryapp.dto.request.vaccine.VaccineUpdateRequest;
import com.patika.cohort3.veterinaryapp.dto.response.vaccine.VaccineResponse;
import com.patika.cohort3.veterinaryapp.entity.Vaccine;
import com.patika.cohort3.veterinaryapp.mapper.VaccineMapper;
import com.patika.cohort3.veterinaryapp.result.Result;
import com.patika.cohort3.veterinaryapp.result.ResultData;
import com.patika.cohort3.veterinaryapp.service.abstracts.VaccineService;
import com.patika.cohort3.veterinaryapp.utilities.ResultHelper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/v1/vaccines")
@RequiredArgsConstructor
public class VaccineController {
    private final VaccineService vaccineService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<VaccineResponse> save(@RequestBody VaccineSaveRequest request) {
        Vaccine saveVaccine = VaccineMapper.INSTANCE.asEntity(request);
        this.vaccineService.save(saveVaccine);
        VaccineResponse vaccineResponse = VaccineMapper.INSTANCE.mapToResponse(saveVaccine);
        return ResultHelper.created(vaccineResponse);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> update(@Valid @RequestBody VaccineUpdateRequest request) {
        Vaccine updateVaccine = VaccineMapper.INSTANCE.asEntity(request);
        this.vaccineService.update(updateVaccine);
        VaccineResponse vaccineResponse = VaccineMapper.INSTANCE.mapToResponse(updateVaccine);
        return ResultHelper.success(vaccineResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete (@PathVariable("id") Long id) {
        this.vaccineService.delete(id);
        return ResultHelper.ok();
    }


    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<VaccineResponse>> findAll() {
        List<Vaccine> vaccines = this.vaccineService.findAll();
        List<VaccineResponse> responses = VaccineMapper.INSTANCE.mapToResponse(vaccines);
        return ResultHelper.success(responses);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> findById(@PathVariable("id") Long id) {
        Vaccine vaccine = this.vaccineService.getById(id);
        VaccineResponse response = VaccineMapper.INSTANCE.mapToResponse(vaccine);
        return ResultHelper.success(response);
    }

    @GetMapping("/expiring-soon")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<VaccineResponse>> getVaccinesByProtectionFinishDateRange(
            @RequestParam String startDate,@RequestParam String endDate) {
;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);

        List<Vaccine> vaccines = vaccineService.getVaccinesByProtectionFinishDateBetween(start, end);
        List<VaccineResponse> responses = VaccineMapper.INSTANCE.mapToResponse(vaccines);
        return ResultHelper.success(responses);
    }




}
