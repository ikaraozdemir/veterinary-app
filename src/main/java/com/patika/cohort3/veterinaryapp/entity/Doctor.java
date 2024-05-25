package com.patika.cohort3.veterinaryapp.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String mpNo;
    @Email
    @Column(unique = true, nullable = false)
    private String mail;
    private String address;
    private String city;

    @OneToMany (mappedBy = "doctor", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<AvailableDate> availableDates;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Appointment> appointments;

}
