package com.patika.cohort3.veterinaryapp.dto.request.customer;
import lombok.Data;

@Data
public class CustomerSaveRequest {
    private String name;
    private String mpNo;
    private String mail;
    private String address;
    private String city;
}
