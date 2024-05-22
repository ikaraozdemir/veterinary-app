package com.patika.cohort3.veterinaryapp.mapper;


import com.patika.cohort3.veterinaryapp.dto.request.doctor.DoctorSaveRequest;
import com.patika.cohort3.veterinaryapp.dto.request.doctor.DoctorUpdateRequest;
import com.patika.cohort3.veterinaryapp.dto.request.vaccine.VaccineSaveRequest;
import com.patika.cohort3.veterinaryapp.dto.request.vaccine.VaccineUpdateRequest;
import com.patika.cohort3.veterinaryapp.dto.response.doctor.DoctorResponse;
import com.patika.cohort3.veterinaryapp.dto.response.doctor.DoctorSaveResponse;
import com.patika.cohort3.veterinaryapp.dto.response.vaccine.VaccineResponse;
import com.patika.cohort3.veterinaryapp.entity.Doctor;
import com.patika.cohort3.veterinaryapp.entity.Vaccine;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface VaccineMapper {
    VaccineMapper INSTANCE = Mappers.getMapper(VaccineMapper.class);
    Vaccine asEntity(VaccineSaveRequest vaccineSaveRequest);
    Vaccine asEntity(VaccineUpdateRequest vaccineUpdateRequest);
    VaccineResponse mapToResponse(Vaccine vaccine);
    List<VaccineResponse> mapToResponse(List<Vaccine> vaccines);

}
