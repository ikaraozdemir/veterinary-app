package com.patika.cohort3.veterinaryapp.service.abstracts;

import com.patika.cohort3.veterinaryapp.entity.Customer;

import java.util.List;

public interface CustomerService {
     Customer save(Customer customer);
     Customer update(Customer customer);
     boolean delete(Long id);
     Customer getById(Long id);
     List<Customer> findAll();
     Customer findByName(String name);

}
