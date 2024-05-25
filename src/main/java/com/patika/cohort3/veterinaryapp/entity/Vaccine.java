package com.patika.cohort3.veterinaryapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Vaccine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String code;
    @Column(nullable = false)
    private LocalDate protectionStartDate;
    @Column(nullable = false)
    private LocalDate protectionFinishDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Animal animal;




}
