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
        doctor.setMail(doctor.getMail().toLowerCase().trim());
        doctor.setMpNo(StringUtils.removeSpaces(doctor.getMpNo()));
        doctor.setName(StringUtils.normalizeSpaces(doctor.getName()));

        Optional<Doctor> optionalDoctor =this.doctorRepository.findByMail(doctor.getMail());
        if (optionalDoctor.isPresent()) {
            throw new AlreadyExistsException("Doctor already exists.");
        }

        Optional<Doctor> optionalDoctor2 = this.doctorRepository.findByMpNo(doctor.getMpNo());
        if (optionalDoctor2.isPresent()) {
            throw new AlreadyExistsException("This phone number is already in use. Please enter a different phone number.");
        }

        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor update(Doctor doctor) {
        this.getById(doctor.getId());
        doctor.setMail(doctor.getMail().toLowerCase().trim());
        doctor.setMpNo(StringUtils.removeSpaces(doctor.getMpNo()));
        doctor.setName(StringUtils.normalizeSpaces(doctor.getName()));

        Optional<Doctor> optionalDoctor =this.doctorRepository.findByMail(doctor.getMail());
        if (optionalDoctor.isPresent() && !optionalDoctor.get().getId().equals(doctor.getId())) {
            throw new AlreadyExistsException("Doctor already exists.");
        }

        Optional<Doctor> optionalDoctor2 = this.doctorRepository.findByMpNo(doctor.getMpNo());
        if (optionalDoctor2.isPresent() && !optionalDoctor2.get().getId().equals(doctor.getId())) {
            throw new AlreadyExistsException("This phone number is already in use. Please enter a different phone number.");
        }

        return doctorRepository.save(doctor);

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
