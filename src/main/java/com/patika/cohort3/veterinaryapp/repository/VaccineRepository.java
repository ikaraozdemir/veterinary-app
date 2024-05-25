package com.patika.cohort3.veterinaryapp.repository;

import com.patika.cohort3.veterinaryapp.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Long> {
    List<Vaccine> findVaccineByNameAndAnimalId(String name, Long id);
    List<Vaccine> findByProtectionFinishDateBetween(LocalDate startDate, LocalDate endDate);
    Optional<Vaccine> findVaccineByCode(String code);
}
