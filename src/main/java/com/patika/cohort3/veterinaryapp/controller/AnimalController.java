package com.patika.cohort3.veterinaryapp.controller;


import com.patika.cohort3.veterinaryapp.dto.request.animal.AnimalSaveRequest;
import com.patika.cohort3.veterinaryapp.dto.request.animal.AnimalUpdateRequest;
import com.patika.cohort3.veterinaryapp.dto.request.customer.CustomerSaveRequest;
import com.patika.cohort3.veterinaryapp.dto.response.animal.AnimalGetVaccinesResponse;
import com.patika.cohort3.veterinaryapp.dto.response.animal.AnimalResponse;
import com.patika.cohort3.veterinaryapp.dto.response.customer.CustomerResponse;
import com.patika.cohort3.veterinaryapp.dto.response.customer.CustomerSaveResponse;
import com.patika.cohort3.veterinaryapp.entity.Animal;
import com.patika.cohort3.veterinaryapp.entity.Customer;
import com.patika.cohort3.veterinaryapp.mapper.AnimalMapper;
import com.patika.cohort3.veterinaryapp.mapper.CustomerMapper;
import com.patika.cohort3.veterinaryapp.result.Result;
import com.patika.cohort3.veterinaryapp.result.ResultData;
import com.patika.cohort3.veterinaryapp.service.abstracts.AnimalService;
import com.patika.cohort3.veterinaryapp.utilities.ResultHelper;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/animals")
@RequiredArgsConstructor
public class AnimalController {
    private final AnimalService animalService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AnimalResponse> save(@RequestBody AnimalSaveRequest request) {
        Animal saveAnimal = AnimalMapper.INSTANCE.asEntity(request);
        this.animalService.save(saveAnimal);
        AnimalResponse animalResponse = AnimalMapper.INSTANCE.mapToResponse(saveAnimal);
        return ResultHelper.created(animalResponse);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> update(@Valid @RequestBody AnimalUpdateRequest request) {
        Animal updateAnimal = AnimalMapper.INSTANCE.asEntity(request);
        this.animalService.update(updateAnimal);
        AnimalResponse animalResponse = AnimalMapper.INSTANCE.mapToResponse(updateAnimal);
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
    public ResultData<List<AnimalResponse>> findAll() {
        List<Animal> animals = this.animalService.findAll();
        List<AnimalResponse> responses = AnimalMapper.INSTANCE.mapToResponse(animals);
        return ResultHelper.success(responses);
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> findByName(@PathParam("name") String name ) {
        Animal animal = this.animalService.findByName(name);
        AnimalResponse animalResponse = AnimalMapper.INSTANCE.mapToResponse(animal);
        return ResultHelper.success(animalResponse);
    }
    @GetMapping("/animal/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> findById(@PathVariable("id") Long id) {
        Animal animal = this.animalService.getById(id);
        AnimalResponse animalResponse = AnimalMapper.INSTANCE.mapToResponse(animal);
        return ResultHelper.success(animalResponse);
    }

    @GetMapping("/customer/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalResponse>> findAnimalsByCustomerId(@PathVariable("customerId") Long id ) {
        List<Animal> animals = this.animalService.findAnimalsByCustomerId(id);
        List<AnimalResponse> responses = AnimalMapper.INSTANCE.mapToResponse(animals);
        return ResultHelper.success(responses);
    }

    @GetMapping("/{id}/vaccines")
    public ResultData<AnimalGetVaccinesResponse>  findVaccinesByAnimalId(@PathVariable("id") Long id) {
        Animal animal = this.animalService.getById(id);
        System.out.println(animal.getVaccines().get(0).getName());
        AnimalGetVaccinesResponse animalGetVaccinesResponse = AnimalMapper.INSTANCE.mapToGetVaccineResponse(animal);
        return ResultHelper.success(animalGetVaccinesResponse);
    }
}
