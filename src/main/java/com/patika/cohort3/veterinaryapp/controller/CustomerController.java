package com.patika.cohort3.veterinaryapp.controller;

import com.patika.cohort3.veterinaryapp.dto.request.customer.CustomerSaveRequest;
import com.patika.cohort3.veterinaryapp.dto.request.customer.CustomerUpdateRequest;
import com.patika.cohort3.veterinaryapp.dto.response.customer.CustomerGetAnimalsResponse;
import com.patika.cohort3.veterinaryapp.dto.response.customer.CustomerResponse;
import com.patika.cohort3.veterinaryapp.dto.response.customer.CustomerSaveResponse;
import com.patika.cohort3.veterinaryapp.entity.Customer;
import com.patika.cohort3.veterinaryapp.mapper.CustomerMapper;
import com.patika.cohort3.veterinaryapp.result.Result;
import com.patika.cohort3.veterinaryapp.result.ResultData;
import com.patika.cohort3.veterinaryapp.service.abstracts.CustomerService;
import com.patika.cohort3.veterinaryapp.utilities.ResultHelper;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CustomerSaveResponse> save(@RequestBody CustomerSaveRequest request) {
        Customer saveCustomer = CustomerMapper.INSTANCE.asEntity(request);
        this.customerService.save(saveCustomer);
        CustomerSaveResponse customerSaveResponse = CustomerMapper.INSTANCE.mapToSaveResponse(saveCustomer);
        return ResultHelper.created(customerSaveResponse);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<CustomerResponse>> findAll() {
        List<Customer> customers = this.customerService.findAll();
        List<CustomerResponse> responses = CustomerMapper.INSTANCE.mapToResponse(customers);
        return ResultHelper.success(responses);
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> findByName(@PathParam("name") String name ) {
        Customer customer = this.customerService.findByName(name);
        CustomerResponse customerResponse = CustomerMapper.INSTANCE.mapToResponse(customer);
        return ResultHelper.success(customerResponse);
    }

    @GetMapping("/customer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> findByName(@PathVariable("id") Long id ) {
        Customer customer = this.customerService.getById(id);
        CustomerResponse customerResponse = CustomerMapper.INSTANCE.mapToResponse(customer);
        return ResultHelper.success(customerResponse);
    }

    @GetMapping("/{id}/animals")
        public ResultData<CustomerGetAnimalsResponse>  findAnimalsByCustomerId(@PathVariable("id") Long id) {
        Customer customer = this.customerService.getById(id);
        CustomerGetAnimalsResponse customerGetAnimalsResponse = CustomerMapper.INSTANCE.mapToGetAnimalResponse(customer);
        return ResultHelper.success(customerGetAnimalsResponse);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> update(@Valid @RequestBody CustomerUpdateRequest request) {
        Customer updateCustomer = CustomerMapper.INSTANCE.asEntity(request);
        this.customerService.update(updateCustomer);
        CustomerResponse customerResponse = CustomerMapper.INSTANCE.mapToResponse(updateCustomer);
        return ResultHelper.success(customerResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete (@PathVariable("id") Long id) {
        this.customerService.delete(id);
        return ResultHelper.ok();
    }

}
