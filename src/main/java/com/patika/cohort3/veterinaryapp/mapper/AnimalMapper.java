package com.patika.cohort3.veterinaryapp.mapper;

import com.patika.cohort3.veterinaryapp.dto.request.animal.AnimalSaveRequest;
import com.patika.cohort3.veterinaryapp.dto.request.animal.AnimalUpdateRequest;
import com.patika.cohort3.veterinaryapp.dto.response.animal.AnimalGetVaccinesResponse;
import com.patika.cohort3.veterinaryapp.dto.response.animal.AnimalResponse;
import com.patika.cohort3.veterinaryapp.entity.Animal;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel="spring" )
public interface AnimalMapper {
    AnimalMapper INSTANCE = Mappers.getMapper(AnimalMapper.class);
    Animal asEntity(AnimalSaveRequest animalSaveRequest);
    Animal asEntity(AnimalUpdateRequest animalUpdateRequest);
    //    AnimalResponse mapToSaveResponse(Customer customer);
    AnimalResponse mapToResponse(Animal animal);
    AnimalGetVaccinesResponse mapToGetVaccineResponse(Animal animal);
    List<AnimalResponse> mapToResponse(List<Animal> animals);
}