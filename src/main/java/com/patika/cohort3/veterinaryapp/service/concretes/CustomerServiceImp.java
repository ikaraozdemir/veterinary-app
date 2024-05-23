package com.patika.cohort3.veterinaryapp.service.concretes;

import com.patika.cohort3.veterinaryapp.entity.Customer;
import com.patika.cohort3.veterinaryapp.exception.AlreadyExistsException;
import com.patika.cohort3.veterinaryapp.exception.NotFoundException;
import com.patika.cohort3.veterinaryapp.repository.CustomerRepository;
import com.patika.cohort3.veterinaryapp.service.abstracts.CustomerService;
import com.patika.cohort3.veterinaryapp.utilities.Message;
import com.patika.cohort3.veterinaryapp.utilities.StringUtils;
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
        customer.setMail(customer.getMail().toLowerCase().trim());

        Optional<Customer> optionalCustomer = this.customerRepository.findByMail(customer.getMail());
        if (optionalCustomer.isPresent()) {
            throw new AlreadyExistsException("Customer already exists.");
        }
        try {
            customer.setMpNo(StringUtils.removeSpaces(customer.getMpNo()));
            customer.setName(StringUtils.normalizeSpaces(customer.getName()));
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
        customer.setMail(customer.getMail().toLowerCase().trim());
        Optional<Customer> optionalCustomer = this.customerRepository.findByMail(customer.getMail());
        if (optionalCustomer.isPresent() && !(optionalCustomer.get().getId().equals(customer.getId())) ) {
            throw new AlreadyExistsException("Customer already exists.");
        }
        try {
            customer.setMpNo(StringUtils.removeSpaces(customer.getMpNo()));
            customer.setName(StringUtils.normalizeSpaces(customer.getName()));
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
    public boolean delete(Long id) {
        Customer customer = this.getById(id);
        this.customerRepository.delete(customer);
        return true;
    }

    @Override
    public Customer getById(Long id) {
        return this.customerRepository.findById(id).orElseThrow(() -> new NotFoundException("No customer found with ID " + id));
    }

    @Override
    public List<Customer> findAll() {
        return this.customerRepository.findAll();
    }

    @Override
    public Customer findByName(String name) {
        return this.customerRepository.findByName(name).orElseThrow(() -> new NotFoundException("No customer found with ID " + name));
    }
}
