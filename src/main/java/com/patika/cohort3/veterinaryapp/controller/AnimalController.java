package com.patika.cohort3.veterinaryapp.controller;


import com.patika.cohort3.veterinaryapp.dto.request.animal.AnimalSaveRequest;
import com.patika.cohort3.veterinaryapp.dto.request.animal.AnimalUpdateRequest;
import com.patika.cohort3.veterinaryapp.dto.response.animal.AnimalGetVaccinesResponse;
import com.patika.cohort3.veterinaryapp.dto.response.animal.AnimalResponse;
import com.patika.cohort3.veterinaryapp.entity.Animal;
import com.patika.cohort3.veterinaryapp.mapper.AnimalMapper;
import com.patika.cohort3.veterinaryapp.result.Result;
import com.patika.cohort3.veterinaryapp.result.ResultData;
import com.patika.cohort3.veterinaryapp.service.abstracts.AnimalService;
import com.patika.cohort3.veterinaryapp.utilities.ResultHelper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/animals")
@RequiredArgsConstructor
public class AnimalController {
    private final AnimalService animalService;
    private final AnimalMapper animalMapper;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AnimalResponse> save(@Valid @RequestBody AnimalSaveRequest request) {
        Animal saveAnimal = this.animalMapper.asEntity(request);
        this.animalService.save(saveAnimal);
        AnimalResponse animalResponse = this.animalMapper.mapToResponse(saveAnimal);
        return ResultHelper.created(animalResponse);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> update(@Valid @RequestBody AnimalUpdateRequest request) {
        Animal updateAnimal = this.animalMapper.asEntity(request);
        Animal updatedAnimal = this.animalService.update(updateAnimal);
        AnimalResponse animalResponse = this.animalMapper.mapToResponse(updatedAnimal);
        return ResultHelper.success(animalResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete (@PathVariable("id") Long id) {
        this.animalService.delete(id);
        return ResultHelper.ok();
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalResponse>> findAll(
            @RequestParam(value = "customerId", required = false) Long customerId) {
        List<Animal> animals = customerId == null
                ? this.animalService.findAll()
                : this.animalService.findAnimalsByCustomerId(customerId);
        List<AnimalResponse> responses = this.animalMapper.mapToResponse(animals);
        return ResultHelper.success(responses);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> findByName(@RequestParam("name") String name ) {
        Animal animal = this.animalService.findByName(name);
        AnimalResponse animalResponse = this.animalMapper.mapToResponse(animal);
        return ResultHelper.success(animalResponse);
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> findById(@PathVariable("id") Long id) {
        Animal animal = this.animalService.getById(id);
        AnimalResponse animalResponse = this.animalMapper.mapToResponse(animal);
        return ResultHelper.success(animalResponse);
    }

    @GetMapping("/{id}/vaccines")
    public ResultData<AnimalGetVaccinesResponse>  findVaccinesByAnimalId(@PathVariable("id") Long id) {
        Animal animal = this.animalService.getById(id);
        AnimalGetVaccinesResponse animalGetVaccinesResponse = this.animalMapper.mapToGetVaccineResponse(animal);
        return ResultHelper.success(animalGetVaccinesResponse);
    }
}
