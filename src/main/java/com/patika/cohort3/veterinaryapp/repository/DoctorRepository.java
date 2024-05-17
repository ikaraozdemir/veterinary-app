package com.patika.cohort3.veterinaryapp.repository;

import com.patika.cohort3.veterinaryapp.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByMail(String mail);

}