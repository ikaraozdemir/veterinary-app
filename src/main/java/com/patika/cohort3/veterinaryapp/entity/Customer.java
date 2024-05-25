package com.patika.cohort3.veterinaryapp.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"mpNo"}, name = "uk_mpno")})
public class Customer {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String mpNo;
    @Email
    @Column(unique = true, nullable = false)
    private String mail;
    private String address;
    private String city;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private List<Animal> animals;
}
