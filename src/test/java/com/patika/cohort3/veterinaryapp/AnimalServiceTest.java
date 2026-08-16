package com.patika.cohort3.veterinaryapp;

import com.patika.cohort3.veterinaryapp.dto.request.animal.AnimalCustomerRequest;
import com.patika.cohort3.veterinaryapp.dto.request.animal.AnimalUpdateRequest;
import com.patika.cohort3.veterinaryapp.dto.request.customer.CustomerUpdateRequest;
import com.patika.cohort3.veterinaryapp.entity.Animal;
import com.patika.cohort3.veterinaryapp.entity.Customer;
import com.patika.cohort3.veterinaryapp.exception.AlreadyExistsException;
import com.patika.cohort3.veterinaryapp.repository.AnimalRepository;
import com.patika.cohort3.veterinaryapp.service.abstracts.CustomerService;
import com.patika.cohort3.veterinaryapp.service.concretes.AnimalServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class AnimalServiceTest {

    @Mock
    private AnimalRepository animalRepository;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private AnimalServiceImp animalService;

    @Test
    void save_WhenAnimalDoesNotExist_ShouldSaveSuccessfully() {
        //GIVEN
        Customer customer = new Customer();
        customer.setId(1L);

        Animal inputAnimal = new Animal();
        inputAnimal.setName("  Tekir  ");
        inputAnimal.setCustomer(customer);

        Animal savedAnimal = new Animal();
        savedAnimal.setId(100L);
        savedAnimal.setName("Tekir");
        savedAnimal.setCustomer(customer);

        //mock davranışları
        when(animalRepository.findAnimalByNameAndCustomerId("Tekir", 1L))
                .thenReturn (Optional.empty());

        when(customerService.getById(1L)).thenReturn(customer);
        when(animalRepository.save(any(Animal.class))).thenReturn(savedAnimal);

        //WHEN (Eylemi gerçekleştirme)
        Animal result = animalService.save(inputAnimal);

        //THEN
        assertNotNull(result);
        assertEquals(100L, result.getId());
        assertEquals("Tekir", result.getName());

        verify(animalRepository,times(1)).save(any(Animal.class));
    }

    @Test
    void save_WhenAnimalAlreadyExists_ShouldThrowAlreadyExistsException() {
        // GIVEN
        Customer customer = new Customer();
        customer.setId(1L);

        Animal animal = new Animal();
        animal.setName("Tekir");
        animal.setCustomer(customer);

        when(animalRepository.findAnimalByNameAndCustomerId(any(String.class), any(Long.class)))
                .thenReturn(Optional.of(animal));

        // WHEN & THEN
        assertThrows(AlreadyExistsException.class, () -> {
            animalService.save(animal);
        });

        verify(animalRepository, never()).save(any(Animal.class));

    }

    @Test
    void update_WhenAnimalExistsAndNoDuplicate_ShouldUpdateSuccessfully() {

        //GIVEN
        Customer customer = new Customer();
        customer.setId(1L);

        AnimalUpdateRequest updatedAnimalRequest = new AnimalUpdateRequest();
        updatedAnimalRequest.setId(10L);
        updatedAnimalRequest.setName(" Yeni İsim ");

        AnimalCustomerRequest customerRequest = new AnimalCustomerRequest();
        customerRequest.setId(1L);
        updatedAnimalRequest.setCustomer(customerRequest);

        Animal existingAnimal = new Animal();
        existingAnimal.setId(10L);
        existingAnimal.setName("Eski İsim");
        existingAnimal.setCustomer(customer);

        when(animalRepository.findById(10L)).thenReturn(Optional.of(existingAnimal));

        when(animalRepository.findAnimalByNameAndCustomerId(any(String.class), any(Long.class)))
                .thenReturn(Optional.empty());

        when(customerService.getById(1L)).thenReturn(customer);

        when(animalRepository.save(any(Animal.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Animal result = animalService.update(updatedAnimalRequest);

        assertNotNull(result);
        assertEquals("Yeni İsim", result.getName());
        verify(animalRepository, times(1)).save(any(Animal.class));
    }
}
