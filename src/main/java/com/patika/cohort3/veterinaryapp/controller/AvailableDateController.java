package com.patika.cohort3.veterinaryapp.controller;

import com.patika.cohort3.veterinaryapp.dto.request.available.date.AvailableDateSaveRequest;
import com.patika.cohort3.veterinaryapp.dto.request.available.date.AvailableDateUpdateRequest;
import com.patika.cohort3.veterinaryapp.dto.response.available.date.AvailableDateResponse;
import com.patika.cohort3.veterinaryapp.entity.AvailableDate;

import com.patika.cohort3.veterinaryapp.mapper.AvailableDateMapper;
import com.patika.cohort3.veterinaryapp.result.Result;
import com.patika.cohort3.veterinaryapp.result.ResultData;
import com.patika.cohort3.veterinaryapp.service.abstracts.AvailableDateService;
import com.patika.cohort3.veterinaryapp.utilities.ResultHelper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/available-dates")
@RequiredArgsConstructor
public class AvailableDateController {
    private final AvailableDateService availableDateService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AvailableDateResponse> save(@RequestBody AvailableDateSaveRequest request) {
        AvailableDate saveAvailableDate = AvailableDateMapper.INSTANCE.asEntity(request);
        this.availableDateService.save(saveAvailableDate);
        AvailableDateResponse availableDateResponse = AvailableDateMapper.INSTANCE.mapToResponse(saveAvailableDate);
        return ResultHelper.created(availableDateResponse);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDateResponse> update(@Valid @RequestBody AvailableDateUpdateRequest request) {
        AvailableDate updateAvailableDate = AvailableDateMapper.INSTANCE.asEntity(request);
        this.availableDateService.update(updateAvailableDate);
        AvailableDateResponse availableDateResponse = AvailableDateMapper.INSTANCE.mapToResponse(updateAvailableDate);
        return ResultHelper.success(availableDateResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete (@PathVariable("id") Long id) {
        this.availableDateService.delete(id);
        return ResultHelper.ok();
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AvailableDateResponse>> findAll() {
        List<AvailableDate> availableDates = this.availableDateService.findAll();
        List<AvailableDateResponse> responses = AvailableDateMapper.INSTANCE.mapToResponse(availableDates);
        return ResultHelper.success(responses);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDateResponse> findById(@PathVariable("id") Long id) {
        AvailableDate availableDate = this.availableDateService.getById(id);
        AvailableDateResponse response = AvailableDateMapper.INSTANCE.mapToResponse(availableDate);
        return ResultHelper.success(response);
    }

}
