package com.patika.cohort3.veterinaryapp.mapper;

import com.patika.cohort3.veterinaryapp.dto.request.doctor.DoctorSaveRequest;
import com.patika.cohort3.veterinaryapp.dto.request.doctor.DoctorUpdateRequest;
import com.patika.cohort3.veterinaryapp.dto.response.doctor.DoctorResponse;
import com.patika.cohort3.veterinaryapp.dto.response.doctor.DoctorSaveResponse;
import com.patika.cohort3.veterinaryapp.entity.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DoctorMapper {
    DoctorMapper INSTANCE = Mappers.getMapper(DoctorMapper.class);
    Doctor asEntity(DoctorSaveRequest doctorSaveRequest);
    Doctor asEntity(DoctorUpdateRequest doctorUpdateRequest);
    DoctorSaveResponse mapToSaveResponse(Doctor doctor);
    DoctorResponse mapToResponse(Doctor Doctor);
    List<DoctorResponse> mapToResponse(List<Doctor> doctor);

}
