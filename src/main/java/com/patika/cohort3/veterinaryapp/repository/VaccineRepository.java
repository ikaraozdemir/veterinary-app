package com.patika.cohort3.veterinaryapp.repository;

import com.patika.cohort3.veterinaryapp.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Long> {
    Optional<Vaccine> findVaccineByCodeAndAnimalId(String code, Long id);
    List<Vaccine> findVaccineByNameAndAnimalId(String name, Long id);
    List<Vaccine> findByProtectionFinishDateBetween(LocalDate startDate, LocalDate endDate);

}
