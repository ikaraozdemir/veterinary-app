package com.patika.cohort3.veterinaryapp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import com.patika.cohort3.veterinaryapp.repository.AppointmentRepository;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import com.patika.cohort3.veterinaryapp.service.concretes.AppointmentServiceImp;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import com.patika.cohort3.veterinaryapp.exception.AlreadyExistsException;
import com.patika.cohort3.veterinaryapp.entity.Appointment;

import com.patika.cohort3.veterinaryapp.service.abstracts.AnimalService;
import com.patika.cohort3.veterinaryapp.service.abstracts.DoctorService;
import com.patika.cohort3.veterinaryapp.entity.Doctor;
import com.patika.cohort3.veterinaryapp.entity.Animal;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;



@ExtendWith(MockitoExtension.class)
public class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;


    @InjectMocks
    private AppointmentServiceImp appointmentService;

@Mock private DoctorService doctorService;
@Mock private AnimalService animalService;

    @Test 
    void update_WhenAnimalHasAlreadyScheduledAppointment_ShouldThrowAlreadyExistsException() {

        Doctor doctor = new Doctor();
        doctor.setId(10L);

        Animal animal = new Animal();
        animal.setId(20L);
        

        Appointment existingAppointment = new Appointment();
        existingAppointment.setId(1L);
        existingAppointment.setDoctor(doctor);
        existingAppointment.setAnimal(animal);
        existingAppointment.setAppointmentDate(java.time.LocalDateTime.now().plusDays(1).withHour(10).withMinute(0));

        
        Appointment animalAppointment = new Appointment();
        animalAppointment.setId(2L);


        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(existingAppointment));

        when(doctorService.getById(10L)).thenReturn(doctor);
        when(animalService.getById(20L)).thenReturn(animal);
        when(doctorService.findByIdAndAvailableDatesDate(any(), any())).thenReturn(doctor);
        when(appointmentRepository.findAppointmentByAppointmentDateAndDoctorId(any(), any()))
        .thenReturn(Optional.empty());

        when(appointmentRepository.findAppointmentByAppointmentDateAndAnimalId(any(), any()))
        .thenReturn(Optional.of(animalAppointment));

        assertThrows(AlreadyExistsException.class, () -> {
            appointmentService.update(existingAppointment);
        });

        verify(appointmentRepository, times(1)).findAppointmentByAppointmentDateAndAnimalId(any(), any());
    }
 
}
