package com.patika.cohort3.veterinaryapp.service.abstracts;

import com.patika.cohort3.veterinaryapp.entity.Animal;
import com.patika.cohort3.veterinaryapp.entity.Vaccine;

import java.util.List;

public interface AnimalService {
    public Animal save(Animal animal);
    public Animal update(Animal animal);
    public boolean delete(Long id);
    public Animal getById(Long id);
    public Animal findByName(String name);
    public List<Animal> findAll();
    public List<Animal> findAnimalsByCustomerId(Long id);
}
