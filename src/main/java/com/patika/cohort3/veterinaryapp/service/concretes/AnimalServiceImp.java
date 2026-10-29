package com.patika.cohort3.veterinaryapp.service.concretes;

import com.patika.cohort3.veterinaryapp.entity.Animal;
import com.patika.cohort3.veterinaryapp.entity.Customer;
import com.patika.cohort3.veterinaryapp.exception.AlreadyExistsException;
import com.patika.cohort3.veterinaryapp.exception.NotFoundException;
import com.patika.cohort3.veterinaryapp.mapper.AnimalMapper;
import com.patika.cohort3.veterinaryapp.repository.AnimalRepository;
import com.patika.cohort3.veterinaryapp.service.abstracts.AnimalService;
import com.patika.cohort3.veterinaryapp.service.abstracts.CustomerService;
import com.patika.cohort3.veterinaryapp.utilities.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimalServiceImp implements AnimalService {
    private final AnimalRepository animalRepository;
    private final CustomerService customerService;

    @Override
    public Animal save(Animal animal) {
        String normalizedName = StringUtils.normalizeSpaces(animal.getName()) ;

        Optional<Animal> optionalAnimal = this.animalRepository.findAnimalByNameAndCustomerId(normalizedName, animal.getCustomer().getId());
        if (optionalAnimal.isPresent()) {
            throw new AlreadyExistsException("Animal already exists.");
        }
        Customer customer = this.customerService.getById(animal.getCustomer().getId());
        animal.setName(normalizedName);
        animal.setCustomer(customer);
        return animalRepository.save(animal);
    }


    @Override
    public Animal update(Animal animal) {

        Animal existingAnimal = this.getById(animal.getId());

        String nameToCheck = animal.getName() != null ? animal.getName() : existingAnimal.getName();
        String normalizedName = StringUtils.normalizeSpaces(nameToCheck);

        Optional<Animal> duplicateAnimal = this.animalRepository.findAnimalByNameAndCustomerId(normalizedName, animal.getCustomer().getId());
        if (duplicateAnimal.isPresent() && !(duplicateAnimal.get().getId().equals(animal.getId()))) {
            throw new AlreadyExistsException("An animal with the same name already exists for this customer.");
        }

        Customer customer = this.customerService.getById(animal.getCustomer().getId());

        AnimalMapper.INSTANCE.updateEntity(animal, existingAnimal);

        existingAnimal.setName(normalizedName);
        existingAnimal.setCustomer(customer);

        return this.animalRepository.save(existingAnimal);
    }

    @Override
    public boolean delete(Long id) {
        Animal animal = this.getById(id);
        this.animalRepository.delete(animal);
        return true;
    }

    @Override
    public Animal getById(Long id) {
        return this.animalRepository.findById(id).orElseThrow(() -> new NotFoundException("No animal found with ID " + id));
    }

    @Override
    public Animal findByName(String name) {
        return this.animalRepository.findByName(StringUtils.normalizeSpaces(name)).orElseThrow(() -> new NotFoundException("No animal found named " + name));
    }

    @Override
    public List<Animal> findAll() {
        return this.animalRepository.findAll();
    }

    @Override
    public List<Animal> findAnimalsByCustomerId(Long id) {
        return this.animalRepository.findAnimalsByCustomerId(id);

    }
}
