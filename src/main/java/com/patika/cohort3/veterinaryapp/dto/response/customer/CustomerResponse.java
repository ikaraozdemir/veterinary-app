package com.patika.cohort3.veterinaryapp.dto.response.customer;

import lombok.Data;

import java.util.List;

@Data
public class CustomerResponse {
    private Long id;
    private String name;
    private String mpNo;
    private String mail;
    private List<CustomerAnimalResponse> animals;
}
