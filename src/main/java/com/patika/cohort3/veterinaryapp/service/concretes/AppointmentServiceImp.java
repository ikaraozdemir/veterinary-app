package com.patika.cohort3.veterinaryapp.service.concretes;

import com.patika.cohort3.veterinaryapp.entity.Animal;
import com.patika.cohort3.veterinaryapp.entity.Appointment;
import com.patika.cohort3.veterinaryapp.entity.Doctor;
import com.patika.cohort3.veterinaryapp.exception.*;
import com.patika.cohort3.veterinaryapp.repository.AppointmentRepository;
import com.patika.cohort3.veterinaryapp.service.abstracts.AnimalService;
import com.patika.cohort3.veterinaryapp.service.abstracts.AppointmentService;
import com.patika.cohort3.veterinaryapp.service.abstracts.DoctorService;
import com.patika.cohort3.veterinaryapp.utilities.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImp implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DoctorService doctorService;
    private final AnimalService animalService;


    @Override
    public Appointment save(Appointment appointment) {

        Doctor doctorWithAvailableDate = this.doctorService.findByIdAndAvailableDatesDate(
                appointment.getDoctor().getId(), appointment.getAppointmentDate().toLocalDate());
        if (doctorWithAvailableDate == null) {
            throw new DoctorNotAvailableException("The doctor is not working on the specified day.");
        }

        Optional<Appointment> optionalAppointment = this.appointmentRepository.findAppointmentByAppointmentDateAndDoctorId(
                appointment.getAppointmentDate(), appointment.getDoctor().getId());
        if (optionalAppointment.isPresent()) {
            throw new AlreadyExistsException("An appointment has already been scheduled for this date and time for this doctor.");
        }

        Optional<Appointment> optionalAppointmentForAnimal = this.appointmentRepository.findAppointmentByAppointmentDateAndAnimalId(
                appointment.getAppointmentDate(), appointment.getAnimal().getId());
        if (optionalAppointmentForAnimal.isPresent()) {
            throw new AlreadyExistsException("An appointment has already been scheduled for this date and time for this animal.");
        }

        if (!(appointment.getAppointmentDate().getMinute() == 0)) {
            throw new DateValidationException("Appointment cannot be scheduled. The time must be at the start of the hour.");
        }

        if (appointment.getAppointmentDate().getHour() < 9 || appointment.getAppointmentDate().getHour() >= 19) {
            throw new DateValidationException(
                    "Appointment cannot be scheduled. " +
                            "The requested appointment time falls outside of our working hours for this doctor.");
        }

        Doctor doctor = this.doctorService.getById(appointment.getDoctor().getId());
        appointment.setDoctor(doctor);

        Animal animal = this.animalService.getById(appointment.getAnimal().getId());
        appointment.setAnimal(animal);
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment update(Appointment appointment) {
        this.getById(appointment.getId());

        Doctor doctorWithAvailableDate = this.doctorService.findByIdAndAvailableDatesDate(
                appointment.getDoctor().getId(), appointment.getAppointmentDate().toLocalDate());

        if (doctorWithAvailableDate == null) {
            throw new DoctorNotAvailableException("The doctor is not available on the specified day.");
        }

        Optional<Appointment> optionalAppointment = this.appointmentRepository.findAppointmentByAppointmentDateAndDoctorId(
                appointment.getAppointmentDate(), appointment.getDoctor().getId());

        if (optionalAppointment.isPresent()) {
            throw new AlreadyExistsException("An appointment has already been scheduled for this date and time for this doctor.");
        }

        Optional<Appointment> optionalAppointmentForAnimal = this.appointmentRepository.findAppointmentByAppointmentDateAndAnimalId(
                appointment.getAppointmentDate(), appointment.getAnimal().getId());

        if (optionalAppointmentForAnimal.isPresent()) {
            throw new AlreadyExistsException("An appointment has already been scheduled for this date and time for this animal.");
        }

        if (!(appointment.getAppointmentDate().getMinute() == 0)) {
            throw new DateValidationException("Appointment cannot be scheduled. The time must be at the start of the hour.");
        }

        if (appointment.getAppointmentDate().getHour() < 9 || appointment.getAppointmentDate().getHour() >= 19) {
            throw new DateValidationException(
                    "Appointment cannot be scheduled. " +
                            "The requested appointment time falls outside of our working hours for this doctor.");
        }

        Doctor optionalDoctor = this.doctorService.getById(appointment.getDoctor().getId());
        appointment.setDoctor(optionalDoctor);

        Animal animal = this.animalService.getById(appointment.getAnimal().getId());
        appointment.setAnimal(animal);
        return this.appointmentRepository.save(appointment);
    }

    @Override
    public boolean delete(Long id) {
        Appointment appointment = this.getById(id);
        this.appointmentRepository.delete(appointment);
        return true;
    }

    @Override
    public Appointment getById(Long id) {
        return this.appointmentRepository.findById(id).orElseThrow(() -> new NotFoundException("No appointment found with ID " + id));
    }

    @Override
    public List<Appointment> findAll() {
        return this.appointmentRepository.findAll();
    }

    @Override
    public List<Appointment> getAppointmentsByAppointmentDateBetweenAndDoctorId(Long id, LocalDateTime startDate, LocalDateTime endDate) {
        return appointmentRepository.findAppointmentsByAppointmentDateBetweenAndDoctorId(startDate, endDate, id);
    }

    @Override
    public List<Appointment> getAppointmentsByAppointmentDateBetweenAndAnimalId(Long id, LocalDateTime startDate, LocalDateTime endDate) {
        return appointmentRepository.findAppointmentsByAppointmentDateBetweenAndAnimalId(startDate, endDate, id);
    }

    @Override
    public List<Appointment> getByDoctorId(Long id) {
        return appointmentRepository.findByDoctorId(id);
    }

}
