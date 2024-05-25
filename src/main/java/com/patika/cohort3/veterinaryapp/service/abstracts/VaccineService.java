package com.patika.cohort3.veterinaryapp.service.abstracts;
import com.patika.cohort3.veterinaryapp.entity.Vaccine;
import java.time.LocalDate;
import java.util.List;


public interface VaccineService {
     Vaccine save(Vaccine vaccine);
     Vaccine update(Vaccine vaccine);
     boolean delete(Long id);
     Vaccine getById(Long id);
     List<Vaccine> findAll();
     List<Vaccine> getVaccinesByProtectionFinishDateBetween(LocalDate startDate, LocalDate endDate);

}
