package com.patika.cohort3.veterinaryapp.service.concretes;

import com.patika.cohort3.veterinaryapp.entity.Appointment;
import com.patika.cohort3.veterinaryapp.entity.AvailableDate;
import com.patika.cohort3.veterinaryapp.entity.Doctor;
import com.patika.cohort3.veterinaryapp.exception.AlreadyExistsException;
import com.patika.cohort3.veterinaryapp.exception.AppointmentExistsException;
import com.patika.cohort3.veterinaryapp.exception.NotFoundException;
import com.patika.cohort3.veterinaryapp.repository.AvailableDateRepository;
import com.patika.cohort3.veterinaryapp.repository.DoctorRepository;
import com.patika.cohort3.veterinaryapp.service.abstracts.AppointmentService;
import com.patika.cohort3.veterinaryapp.service.abstracts.AvailableDateService;
import com.patika.cohort3.veterinaryapp.service.abstracts.DoctorService;
import com.patika.cohort3.veterinaryapp.utilities.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AvailableDateServiceImp implements AvailableDateService {
    private final AvailableDateRepository availableDateRepository;
    private final DoctorService doctorService;
    private final AppointmentService appointmentService;

    @Override
    public AvailableDate save(AvailableDate availableDate) {
        Optional<AvailableDate> optionalAvailableDate = this.availableDateRepository.findAvailableDateByDateAndDoctorId(
                availableDate.getDate(), availableDate.getDoctor().getId());
        if (optionalAvailableDate.isPresent()) {
            throw new AlreadyExistsException("The date for this doctor already exists.");
        }
        Doctor doctor = this.doctorService.getById(availableDate.getDoctor().getId());
        availableDate.setDoctor(doctor);
        return availableDateRepository.save(availableDate);
    }

    @Override
    public AvailableDate update(AvailableDate availableDate) {
        AvailableDate availableDateFromDb = this.getById(availableDate.getId());

        Optional<AvailableDate> optionalAvailableDate = this.availableDateRepository.findAvailableDateByDateAndDoctorId(
                availableDate.getDate(), availableDate.getDoctor().getId());
        if (optionalAvailableDate.isPresent() && !(optionalAvailableDate.get().getId().equals(availableDate.getId()))) {
            throw new AlreadyExistsException("The working day for this doctor already exists.");
        }

        List<Appointment> appointments = this.appointmentService.getByDoctorId(availableDate.getDoctor().getId());
        for(Appointment appointment :appointments) {
            if (!availableDateFromDb.getDate().equals(optionalAvailableDate.get().getDate()) &&
                    (appointment.getAppointmentDate().toLocalDate().isEqual(availableDateFromDb.getDate()))) {
                throw new AppointmentExistsException("Available date with ID " + availableDate.getId() + " for this doctor cannot be changed because there are existing appointments.");
            }
        }

        Doctor doctor = this.doctorService.getById(availableDate.getDoctor().getId());
        availableDate.setDoctor(doctor);
        return this.availableDateRepository.save(availableDate);
    }

    @Override
    public boolean delete(Long id) {
        AvailableDate availableDate = this.getById(id);

        List<Appointment> appointments = this.appointmentService.getByDoctorId(availableDate.getDoctor().getId());
        for(Appointment appointment :appointments) {
            if(appointment.getAppointmentDate().toLocalDate().isEqual(availableDate.getDate())){
                throw new AppointmentExistsException("Available date with ID " + id + " cannot be deleted because there are existing appointments.");
            }
        }

        this.availableDateRepository.delete(availableDate);
        return true;
    }

    @Override
    public AvailableDate getById(Long id) {
        return this.availableDateRepository.findById(id).orElseThrow(() -> new NotFoundException("No available date found with ID " + id));
    }

    @Override
    public List<AvailableDate> findAll() {
        return this.availableDateRepository.findAll();
    }

}
