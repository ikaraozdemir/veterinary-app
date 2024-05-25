package com.patika.cohort3.veterinaryapp.service.abstracts;
import com.patika.cohort3.veterinaryapp.entity.Doctor;

import java.time.LocalDate;
import java.util.List;

public interface DoctorService {
     Doctor save(Doctor doctor);
     Doctor update(Doctor doctor);
     boolean delete(Long id);
     Doctor getById(Long id);
     List<Doctor> findAll();
     Doctor findByIdAndAvailableDatesDate(Long id, LocalDate availableDate);
}
