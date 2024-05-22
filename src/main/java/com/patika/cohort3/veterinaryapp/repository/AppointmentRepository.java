package com.patika.cohort3.veterinaryapp.repository;

import com.patika.cohort3.veterinaryapp.entity.Appointment;
import com.patika.cohort3.veterinaryapp.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Optional<Appointment> findAppointmentByAppointmentDateAndDoctorId(LocalDateTime appointmentDate, Long id);
    Optional<Appointment> findAppointmentByAppointmentDateAndAnimalId(LocalDateTime appointmentDate, Long id);
    List<Appointment> findAppointmentsByAppointmentDateBetweenAndDoctorId(LocalDateTime startDate, LocalDateTime endDate,Long id);
    List<Appointment> findAppointmentsByAppointmentDateBetweenAndAnimalId(LocalDateTime startDate, LocalDateTime endDate,Long id);
    List<Appointment> findByDoctorId(Long id);
}
