package com.patika.cohort3.veterinaryapp.dto.response.customer;

import lombok.Data;

import java.util.List;

@Data
public class CustomerGetAnimalsResponse {
    private Long id;
    private String name;
    private List<CustomerAnimalResponse> animals;
}
