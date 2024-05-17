package com.patika.cohort3.veterinaryapp.service.concretes;

import com.patika.cohort3.veterinaryapp.entity.AvailableDate;
import com.patika.cohort3.veterinaryapp.entity.Doctor;
import com.patika.cohort3.veterinaryapp.exception.AlreadyExistsException;
import com.patika.cohort3.veterinaryapp.exception.NotFoundException;
import com.patika.cohort3.veterinaryapp.repository.AvailableDateRepository;
import com.patika.cohort3.veterinaryapp.repository.DoctorRepository;
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
    private final DoctorRepository doctorRepository;

    @Override
    public AvailableDate save(AvailableDate availableDate) {
        Optional<AvailableDate> optionalAvailableDate = this.availableDateRepository.findAvailableDateByDateAndDoctorId(availableDate.getDate(), availableDate.getDoctor().getId());
        if (optionalAvailableDate.isPresent()) {
            throw new AlreadyExistsException("The date for this doctor already exists.");
        }
        Doctor doctor = this.doctorRepository.findById(availableDate.getDoctor().getId()).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND));
        availableDate.setDoctor(doctor);
        return availableDateRepository.save(availableDate);
    }

    @Override
    public AvailableDate update(AvailableDate availableDate) {
        this.getById(availableDate.getId());
        Doctor doctor = this.doctorRepository.findById(availableDate.getDoctor().getId()).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND));
        availableDate.setDoctor(doctor);
        return this.availableDateRepository.save(availableDate);
    }

    @Override
    public boolean delete(Long id) {
        AvailableDate availableDate = this.getById(id);
        this.availableDateRepository.delete(availableDate);
        return true;
    }

    @Override
    public AvailableDate getById(Long id) {
        return this.availableDateRepository.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND));
    }

    @Override
    public List<AvailableDate> findAll() {
        return this.availableDateRepository.findAll();
    }

    @Override
    public AvailableDate findByName(String name) {
        return null;
    }
}
