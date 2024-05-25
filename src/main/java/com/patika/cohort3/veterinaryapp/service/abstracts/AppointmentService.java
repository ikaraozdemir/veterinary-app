package com.patika.cohort3.veterinaryapp.service.abstracts;

import com.patika.cohort3.veterinaryapp.entity.Appointment;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {
    public Appointment save(Appointment appointment);
    public Appointment update(Appointment appointment);
    public boolean delete(Long id);
    public Appointment getById(Long id);
    public List<Appointment> findAll();
    public List<Appointment> getAppointmentsByAppointmentDateBetweenAndDoctorId(Long id, LocalDateTime startDate, LocalDateTime endDate);
    public List<Appointment> getAppointmentsByAppointmentDateBetweenAndAnimalId(Long id, LocalDateTime startDate, LocalDateTime endDate);
    public List<Appointment> getByDoctorId(Long id);

}
