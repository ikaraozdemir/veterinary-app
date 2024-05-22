package com.patika.cohort3.veterinaryapp.service.abstracts;

import com.patika.cohort3.veterinaryapp.entity.Doctor;
import com.patika.cohort3.veterinaryapp.entity.Vaccine;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;


public interface VaccineService {
    public Vaccine save(Vaccine vaccine);
    public Vaccine update(Vaccine vaccine);
    public boolean delete(Long id);
    public Vaccine getById(Long id);
    public List<Vaccine> findAll();
    public List<Vaccine> getVaccinesByProtectionFinishDateBetween(LocalDate startDate, LocalDate endDate);

}
