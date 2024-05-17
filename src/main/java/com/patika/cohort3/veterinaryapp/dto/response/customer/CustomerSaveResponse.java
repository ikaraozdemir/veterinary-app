package com.patika.cohort3.veterinaryapp.dto.response.customer;

import lombok.Data;

@Data
public class CustomerSaveResponse {
    private Long id;
    private String name;
    private String mpNo;
    private String mail;
}
