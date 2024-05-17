package com.patika.cohort3.veterinaryapp.service.abstracts;


import com.patika.cohort3.veterinaryapp.entity.Doctor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DoctorService {
    public Doctor save(Doctor doctor);
    public Doctor update(Doctor doctor);
    public boolean delete(Long id);
    public Doctor getById(Long id);
    public List<Doctor> findAll();
    public Doctor findByName(String name);
}
