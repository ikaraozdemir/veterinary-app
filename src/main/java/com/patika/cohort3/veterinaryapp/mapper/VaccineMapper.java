package com.patika.cohort3.veterinaryapp.mapper;

import com.patika.cohort3.veterinaryapp.dto.request.vaccine.VaccineSaveRequest;
import com.patika.cohort3.veterinaryapp.dto.request.vaccine.VaccineUpdateRequest;

import com.patika.cohort3.veterinaryapp.dto.response.vaccine.VaccineResponse;

import com.patika.cohort3.veterinaryapp.entity.Vaccine;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
(componentModel = "spring")
public interface VaccineMapper {
    Vaccine asEntity(VaccineSaveRequest vaccineSaveRequest);
    Vaccine asEntity(VaccineUpdateRequest vaccineUpdateRequest);
    VaccineResponse mapToResponse(Vaccine vaccine);
    List<VaccineResponse> mapToResponse(List<Vaccine> vaccines);

}
