package com.patika.cohort3.veterinaryapp.mapper;

import com.patika.cohort3.veterinaryapp.dto.request.animal.AnimalSaveRequest;
import com.patika.cohort3.veterinaryapp.dto.request.animal.AnimalUpdateRequest;
import com.patika.cohort3.veterinaryapp.dto.response.animal.AnimalGetVaccinesResponse;
import com.patika.cohort3.veterinaryapp.dto.response.animal.AnimalResponse;
import com.patika.cohort3.veterinaryapp.entity.Animal;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel="spring" )
public interface AnimalMapper {
    AnimalMapper INSTANCE = Mappers.getMapper(AnimalMapper.class);
    Animal asEntity(AnimalSaveRequest animalSaveRequest);
    Animal asEntity(AnimalUpdateRequest animalUpdateRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "appointments", ignore = true)
    @Mapping(target = "vaccines", ignore = true)
    void updateEntity(Animal source, @MappingTarget Animal target);
    AnimalResponse mapToResponse(Animal animal);
    AnimalGetVaccinesResponse mapToGetVaccineResponse(Animal animal);
    List<AnimalResponse> mapToResponse(List<Animal> animals);
}