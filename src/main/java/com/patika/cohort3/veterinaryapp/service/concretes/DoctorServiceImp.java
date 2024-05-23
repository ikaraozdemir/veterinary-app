package com.patika.cohort3.veterinaryapp.service.concretes;

import com.patika.cohort3.veterinaryapp.entity.Appointment;
import com.patika.cohort3.veterinaryapp.entity.Customer;
import com.patika.cohort3.veterinaryapp.entity.Doctor;
import com.patika.cohort3.veterinaryapp.exception.AlreadyExistsException;
import com.patika.cohort3.veterinaryapp.exception.AppointmentExistsException;
import com.patika.cohort3.veterinaryapp.exception.NotFoundException;
import com.patika.cohort3.veterinaryapp.repository.DoctorRepository;
import com.patika.cohort3.veterinaryapp.service.abstracts.DoctorService;
import com.patika.cohort3.veterinaryapp.utilities.Message;
import com.patika.cohort3.veterinaryapp.utilities.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorServiceImp implements DoctorService {
    private final DoctorRepository doctorRepository;

    @Override
    public Doctor save(Doctor doctor) {
        Optional<Doctor> optionalDoctor =this.doctorRepository.findByMail(doctor.getMail());
        doctor.setMpNo(doctor.getMpNo().toLowerCase().trim());
        if (optionalDoctor.isPresent()) {
            throw new AlreadyExistsException("Doctor already exists.");
        }
        try {
            doctor.setName(StringUtils.normalizeSpaces(doctor.getName()));
            doctor.setMpNo(StringUtils.removeSpaces(doctor.getMpNo()));
            return doctorRepository.save(doctor);
        } catch (DataIntegrityViolationException e) {
            String errorMessage = e.getRootCause().getMessage();
            if (errorMessage.contains("mp_no") && errorMessage.contains("already exists")) {
                throw new DataIntegrityViolationException("This phone number is already in use. Please enter a different phone number.");
            }
            throw e;
        }
    }

    @Override
    public Doctor update(Doctor doctor) {
        this.getById(doctor.getId());
        Optional<Doctor> optionalDoctor =this.doctorRepository.findByMail(doctor.getMail());
        doctor.setMpNo(doctor.getMpNo().toLowerCase().trim());
        if (optionalDoctor.isPresent() && !optionalDoctor.get().getMpNo().equals(doctor.getMpNo())) {
            throw new AlreadyExistsException("Doctor already exists.");
        }
        try {
            doctor.setName(StringUtils.normalizeSpaces(doctor.getName()));
            doctor.setMpNo(StringUtils.removeSpaces(doctor.getMpNo()));
            return doctorRepository.save(doctor);
        } catch (DataIntegrityViolationException e) {
            String errorMessage = e.getRootCause().getMessage();
            if (errorMessage.contains("mp_no") && errorMessage.contains("already exists")) {
                throw new DataIntegrityViolationException("This phone number is already in use. Please enter a different phone number.");
            }
            throw e;
        }
    }

    @Override
    public boolean delete(Long id) {
        Doctor doctor = this.getById(id);

        List<Appointment> appointments = doctor.getAppointments();
        if(!appointments.isEmpty()) {
            throw new AppointmentExistsException("Doctor with ID " + id + " cannot be deleted because there are existing appointments.");
        }
        this.doctorRepository.delete(doctor);
        return true;
    }

    @Override
    public Doctor getById(Long id) {
        return this.doctorRepository.findById(id).orElseThrow(()-> new NotFoundException("No doctor found with ID " + id));
    }

    @Override
    public List<Doctor> findAll() {
        return this.doctorRepository.findAll();
    }

    @Override
    public Doctor findByIdAndAvailableDatesDate(Long id, LocalDate availableDate) {
        return this.doctorRepository.findByIdAndAvailableDatesDate(id,availableDate).orElse(null);
    }
}
