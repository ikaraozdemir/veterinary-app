package com.patika.cohort3.veterinaryapp.controller;

import com.patika.cohort3.veterinaryapp.dto.request.available.date.AvailableDateSaveRequest;
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
    private final AvailableDateMapper availableDateMapper;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AvailableDateResponse> save(@Valid @RequestBody AvailableDateSaveRequest request) {
        AvailableDate saveAvailableDate = this.availableDateMapper.asEntity(request);
        this.availableDateService.save(saveAvailableDate);
        AvailableDateResponse availableDateResponse = this.availableDateMapper.mapToResponse(saveAvailableDate);
        return ResultHelper.created(availableDateResponse);
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
        List<AvailableDateResponse> responses = this.availableDateMapper.mapToResponse(availableDates);
        return ResultHelper.success(responses);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDateResponse> findById(@PathVariable("id") Long id) {
        AvailableDate availableDate = this.availableDateService.getById(id);
        AvailableDateResponse response = this.availableDateMapper.mapToResponse(availableDate);
        return ResultHelper.success(response);
    }

}
