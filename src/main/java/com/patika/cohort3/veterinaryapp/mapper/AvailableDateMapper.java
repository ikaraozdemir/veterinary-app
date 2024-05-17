package com.patika.cohort3.veterinaryapp.mapper;

import com.patika.cohort3.veterinaryapp.dto.request.available.date.AvailableDateSaveRequest;
import com.patika.cohort3.veterinaryapp.dto.request.available.date.AvailableDateUpdateRequest;
import com.patika.cohort3.veterinaryapp.dto.response.available.date.AvailableDateResponse;
import com.patika.cohort3.veterinaryapp.entity.AvailableDate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AvailableDateMapper {
    AvailableDateMapper INSTANCE = Mappers.getMapper(AvailableDateMapper.class);
    AvailableDate asEntity(AvailableDateSaveRequest availableDateSaveRequest);
    AvailableDate asEntity(AvailableDateUpdateRequest availableDateUpdateRequest);
    AvailableDateResponse mapToResponse(AvailableDate availableDate);
    List<AvailableDateResponse> mapToResponse(List<AvailableDate> availableDates);
}
