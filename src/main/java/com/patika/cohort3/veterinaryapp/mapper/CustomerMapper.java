package com.patika.cohort3.veterinaryapp.mapper;

import com.patika.cohort3.veterinaryapp.dto.request.customer.CustomerSaveRequest;
import com.patika.cohort3.veterinaryapp.dto.request.customer.CustomerUpdateRequest;
import com.patika.cohort3.veterinaryapp.dto.response.customer.CustomerResponse;
import com.patika.cohort3.veterinaryapp.dto.response.customer.CustomerSaveResponse;
import com.patika.cohort3.veterinaryapp.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
    Customer asEntity(CustomerSaveRequest customerSaveRequest);
    Customer asEntity(CustomerUpdateRequest customerUpdateRequest);
    CustomerSaveResponse mapToSaveResponse(Customer customer);
    CustomerResponse mapToResponse(Customer customer);
    List<CustomerResponse> mapToResponse(List<Customer> customers);
}
