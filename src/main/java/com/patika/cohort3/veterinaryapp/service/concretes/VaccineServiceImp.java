package com.patika.cohort3.veterinaryapp.service.concretes;

import com.patika.cohort3.veterinaryapp.entity.Animal;
import com.patika.cohort3.veterinaryapp.entity.Vaccine;
import com.patika.cohort3.veterinaryapp.exception.AlreadyExistsException;
import com.patika.cohort3.veterinaryapp.exception.NotFoundException;
import com.patika.cohort3.veterinaryapp.exception.DateValidationException;
import com.patika.cohort3.veterinaryapp.repository.AnimalRepository;
import com.patika.cohort3.veterinaryapp.repository.VaccineRepository;
import com.patika.cohort3.veterinaryapp.service.abstracts.VaccineService;
import com.patika.cohort3.veterinaryapp.utilities.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VaccineServiceImp implements VaccineService {
    private final VaccineRepository vaccineRepository;
    private final AnimalRepository animalRepository;


    @Override
    public Vaccine save(Vaccine vaccine) {

        //Remove extra spaces from name and code.
        String normalizedName = StringUtils.normalizeSpaces(vaccine.getName());
        String trimmedCode = vaccine.getCode().toUpperCase().trim();

        Optional<Vaccine> vaccineForCodeCheck = vaccineRepository.findVaccineByCode(vaccine.getCode());
        if (vaccineForCodeCheck.isPresent()) {
            throw new AlreadyExistsException("This animal has already been given the vaccine with this code.");
        }

        //Check the protection end date for a new dose  and whether the animal has received that vaccine.
        List<Vaccine> vaccines = vaccineRepository.findVaccineByNameAndAnimalId(normalizedName, vaccine.getAnimal().getId());
        for (Vaccine vaccineFromDB : vaccines) {
            if (vaccineFromDB.getProtectionFinishDate().isAfter(LocalDate.now())) {
                throw new DateValidationException("The vaccine's protection end date has not yet arrived.");
            }
        }

        //Check if the protection end date is after protection start date
        if (vaccine.getProtectionFinishDate().isBefore(vaccine.getProtectionStartDate())
                || vaccine.getProtectionFinishDate().isEqual(vaccine.getProtectionStartDate())) {
            throw new DateValidationException("Protection end date should be after protection start date.");
        }

        Optional<Animal> optionalAnimal = this.animalRepository.findById(vaccine.getAnimal().getId());
        vaccine.setAnimal(optionalAnimal.get());
        vaccine.setName(normalizedName);
        vaccine.setCode(trimmedCode);
        return vaccineRepository.save(vaccine);
    }

    @Override
    public Vaccine update(Vaccine vaccine) {

        //Check if vaccine already exists.
        this.getById(vaccine.getId());

        //Remove extra spaces from name and code.
        String normalizedName = StringUtils.normalizeSpaces(vaccine.getName());
        String trimmedCode = vaccine.getCode().toUpperCase().trim();

        Optional<Vaccine> vaccineForCodeCheck = vaccineRepository.findVaccineByCode(vaccine.getCode());
        if (vaccineForCodeCheck.isPresent() && !vaccine.getId().equals(vaccineForCodeCheck.get().getId())) {
            throw new AlreadyExistsException("This animal has already been given the vaccine with this code.");
        }


        //Do not allow the user to update a vaccine if an animal has received multiple doses
        List<Vaccine> vaccines = vaccineRepository.findVaccineByNameAndAnimalId(normalizedName, vaccine.getAnimal().getId());
         for (Vaccine vaccineFromDB : vaccines) {
                if (!Objects.equals(trimmedCode, vaccineFromDB.getCode()) && vaccine.getId().equals(vaccineFromDB.getId())) {
                    throw new AlreadyExistsException(
                            "This animal has been given multiple doses of this vaccine. " +
                                    "Modifications are not allowed for this type of vaccine.");
                }
            }

        if (vaccine.getProtectionFinishDate().isBefore(vaccine.getProtectionStartDate())
                || vaccine.getProtectionFinishDate().isEqual(vaccine.getProtectionStartDate())) {
            throw new DateValidationException("Protection end date should be after protection start date.");
        }

        Optional<Animal> optionalAnimal = this.animalRepository.findById(vaccine.getAnimal().getId());
        vaccine.setAnimal(optionalAnimal.get());
        vaccine.setName(normalizedName);
        vaccine.setCode(trimmedCode);
        return vaccineRepository.save(vaccine);

    }

    @Override
    public boolean delete(Long id) {
        Vaccine vaccine = this.getById(id);
        this.vaccineRepository.delete(vaccine);
        return true;
    }

    @Override
    public Vaccine getById(Long id) {
        return this.vaccineRepository.findById(id).orElseThrow(() -> new NotFoundException("No vaccine found with ID " + id));
    }

    @Override
    public List<Vaccine> findAll() {
        return this.vaccineRepository.findAll();
    }

    @Override
    public List<Vaccine> getVaccinesByProtectionFinishDateBetween(LocalDate startDate, LocalDate endDate) {
        return vaccineRepository.findByProtectionFinishDateBetween(startDate, endDate);
    }

}
