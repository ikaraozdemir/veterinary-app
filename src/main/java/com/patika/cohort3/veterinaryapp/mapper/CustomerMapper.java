package com.patika.cohort3.veterinaryapp.mapper;

import com.patika.cohort3.veterinaryapp.dto.request.customer.CustomerSaveRequest;
import com.patika.cohort3.veterinaryapp.dto.request.customer.CustomerUpdateRequest;
import com.patika.cohort3.veterinaryapp.dto.response.customer.CustomerGetAnimalsResponse;
import com.patika.cohort3.veterinaryapp.dto.response.customer.CustomerResponse;
import com.patika.cohort3.veterinaryapp.dto.response.customer.CustomerSaveResponse;
import com.patika.cohort3.veterinaryapp.entity.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
(componentModel = "spring")
public interface CustomerMapper {

    Customer asEntity(CustomerSaveRequest customerSaveRequest);
    Customer asEntity(CustomerUpdateRequest customerUpdateRequest);
    CustomerSaveResponse mapToSaveResponse(Customer customer);
    CustomerResponse mapToResponse(Customer customer);
    CustomerGetAnimalsResponse mapToGetAnimalResponse(Customer customer);
    List<CustomerResponse> mapToResponse(List<Customer> customers);
}
