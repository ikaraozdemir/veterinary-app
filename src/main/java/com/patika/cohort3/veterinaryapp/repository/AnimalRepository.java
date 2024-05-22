package com.patika.cohort3.veterinaryapp.repository;

import com.patika.cohort3.veterinaryapp.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    Optional<Animal> findAnimalByNameAndCustomerId(String name, Long id);
    Optional<Animal> findByName(String name);
    List<Animal> findAnimalsByCustomerId(Long id);
}
