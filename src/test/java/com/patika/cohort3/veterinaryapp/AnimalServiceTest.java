package com.patika.cohort3.veterinaryapp;

import com.patika.cohort3.veterinaryapp.entity.Animal;
import com.patika.cohort3.veterinaryapp.entity.Customer;
import com.patika.cohort3.veterinaryapp.exception.AlreadyExistsException;
import com.patika.cohort3.veterinaryapp.exception.NotFoundException;
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

        Animal updatedAnimalRequest = new Animal();
        updatedAnimalRequest.setId(10L);
        updatedAnimalRequest.setName(" Yeni İsim ");
        updatedAnimalRequest.setCustomer(customer);

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

    @Test
    void update_WhenAnimalNotFound_ShouldThrowNotFoundException() {
        Animal updatedAnimalRequest = new Animal();
        updatedAnimalRequest.setId(10L);

        when(animalRepository.findById(10L)).thenReturn(Optional.empty());

        // Did it throw the expected exception?
        NotFoundException exception  = assertThrows(NotFoundException.class, () -> {
            animalService.update(updatedAnimalRequest);
        });

        // did it throw with the right text?
        assertEquals("No animal found with ID 10", exception.getMessage());

        // Verify that save was never called
        verify(animalRepository, never()).save(any(Animal.class));

        // Verify that customerService.getById was never reached — the exception came from AnimalServiceImp's own getById via animalRepository.findById.
        verify(customerService, never()).getById(any(Long.class));

        // Verify that findAnimalByNameAndCustomerId was never called since to test the code path, the exception should be thrown before that method is called
        verify(animalRepository, never()).findAnimalByNameAndCustomerId(any(String.class), any(Long.class));
    }

    @Test
    void update_WhenDuplicateAnimalExists_ShouldThrowAlreadyExistsException(){
        Customer customer = new Customer();
        customer.setId(1L);

        Animal updatedAnimal = new Animal();
        updatedAnimal.setId(10L);
        updatedAnimal.setName("Duplicate Name");
        updatedAnimal.setCustomer(customer);
        updatedAnimal.setCustomer(customer);

        Animal existingAnimal = new Animal();
        existingAnimal.setId(20L);
        existingAnimal.setName("Duplicate Name");
        existingAnimal.setCustomer(customer);

        when(animalRepository.findById(10L)).thenReturn(Optional.of(updatedAnimal));

        when(animalRepository.findAnimalByNameAndCustomerId(any(String.class), any(Long.class)))
                .thenReturn(Optional.of(existingAnimal));

        // Did it throw the expected exception?
        AlreadyExistsException exception = assertThrows(AlreadyExistsException.class, () -> {
            animalService.update(updatedAnimal);
        });

        // did it throw with the right text?
        assertEquals("An animal with the same name already exists for this customer.", exception.getMessage());

        // Verify that save was never called
        verify(animalRepository, never()).save(any(Animal.class));
    }

    @Test
    void delete_WhenAnimalExists_ShouldDeleteSuccessfully() {

        Customer customer = new Customer();
        customer.setId(1L);

        Animal existingAnimal = new Animal();
        existingAnimal.setId(10L);
        existingAnimal.setName("AnimalDelete");

        existingAnimal.setCustomer(customer);

        when(animalRepository.findById(10L)).thenReturn(Optional.of(existingAnimal));

        boolean result = animalService.delete(10L);

        assertTrue(result);
        verify(animalRepository, times(1)).delete(existingAnimal);        
    }
}
