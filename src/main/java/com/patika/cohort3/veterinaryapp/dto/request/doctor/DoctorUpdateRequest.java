package com.patika.cohort3.veterinaryapp.dto.request.doctor;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class DoctorUpdateRequest {
    private Long id;
    private String name;
    private String mpNo;

    @Email
    @Column(nullable = true)
    private String mail;
    private String address;
    private String city;
}
