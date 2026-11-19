package com.patika.cohort3.veterinaryapp.mapper;

import com.patika.cohort3.veterinaryapp.dto.request.available.date.AvailableDateSaveRequest;
import com.patika.cohort3.veterinaryapp.dto.response.available.date.AvailableDateResponse;
import com.patika.cohort3.veterinaryapp.entity.AvailableDate;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
(componentModel = "spring")
public interface AvailableDateMapper {

    AvailableDate asEntity(AvailableDateSaveRequest availableDateSaveRequest);
    AvailableDateResponse mapToResponse(AvailableDate availableDate);
    List<AvailableDateResponse> mapToResponse(List<AvailableDate> availableDates);
}
