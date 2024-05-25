package com.patika.cohort3.veterinaryapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String species;
    private String gender;
    private String color;
    private String breed;
    private LocalDate dateOfBirth;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Customer customer;

    @OneToMany(mappedBy = "animal", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "animal", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Vaccine> vaccines;


}
