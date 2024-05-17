package com.patika.cohort3.veterinaryapp.service.abstracts;

import com.patika.cohort3.veterinaryapp.entity.Customer;

import java.util.List;

public interface CustomerService {
    public Customer save(Customer customer);
    public Customer update(Customer customer);
    public boolean delete(Long id);
    public Customer getById(Long id);
    public List<Customer> findAll();
    public Customer findByName(String name);

}
