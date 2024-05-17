package com.patika.cohort3.veterinaryapp.dto.response.doctor;

import lombok.Data;

@Data
public class DoctorSaveResponse {
    private Long id;
    private String name;
    private String mpNo;
    private String mail;
}
