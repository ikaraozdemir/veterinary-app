package com.patika.cohort3.veterinaryapp.service.concretes;

import com.patika.cohort3.veterinaryapp.entity.Customer;
import com.patika.cohort3.veterinaryapp.exception.AlreadyExistsException;
import com.patika.cohort3.veterinaryapp.exception.NotFoundException;
import com.patika.cohort3.veterinaryapp.repository.CustomerRepository;
import com.patika.cohort3.veterinaryapp.service.abstracts.CustomerService;
import com.patika.cohort3.veterinaryapp.utilities.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImp implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public Customer save(Customer customer) {
        Optional<Customer> optionalCustomer =this.customerRepository.findByMail(customer.getMail());
        customer.setMpNo(customer.getMpNo().trim());
        if (optionalCustomer.isPresent()) {
            throw new AlreadyExistsException("Customer already exists.");
        }
        try {
            return customerRepository.save(customer);
        } catch (DataIntegrityViolationException e) {
            String errorMessage = e.getRootCause().getMessage();
            if (errorMessage.contains("mp_no") && errorMessage.contains("already exists")) {
                throw new DataIntegrityViolationException("This phone number is already in use. Please enter a different phone number.");
            }
            throw e;
        }

    }

    @Override
    public Customer update(Customer customer) {
        this.getById(customer.getId());
//        Optional<Customer> optionalCustomer = this.customerRepository.findByMail(customer.getMail());
        return this.customerRepository.save(customer);
    }

    @Override
    public boolean delete(Long id) {
        Customer customer = this.getById(id);
        this.customerRepository.delete(customer);
        return true;
    }

    @Override
    public Customer getById(Long id) {
        return this.customerRepository.findById(id).orElseThrow(() -> new NotFoundException(Message.NOT_FOUND));
    }

    @Override
    public List<Customer> findAll() {
        return this.customerRepository.findAll();
    }

    @Override
    public Customer findByName(String name) {
        return this.customerRepository.findByName(name);
    }
}
