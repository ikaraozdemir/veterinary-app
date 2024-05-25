package com.patika.cohort3.veterinaryapp.service.abstracts;

import com.patika.cohort3.veterinaryapp.entity.Animal;

import java.util.List;

public interface AnimalService {
     Animal save(Animal animal);
     Animal update(Animal animal);
     boolean delete(Long id);
     Animal getById(Long id);
     Animal findByName(String name);
     List<Animal> findAll();
     List<Animal> findAnimalsByCustomerId(Long id);
}
