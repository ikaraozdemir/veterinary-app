package com.patika.cohort3.veterinaryapp.dto.request.doctor;
import lombok.Data;

@Data
public class DoctorSaveRequest {
    private String name;
    private String mpNo;
    private String mail;
    private String address;
    private String city;
}
