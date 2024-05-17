package com.patika.cohort3.veterinaryapp.repository;

import com.patika.cohort3.veterinaryapp.entity.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface AvailableDateRepository extends JpaRepository<AvailableDate, Long> {

    Optional<AvailableDate> findAvailableDateByDateAndDoctorId(LocalDate date, Long id);
}
