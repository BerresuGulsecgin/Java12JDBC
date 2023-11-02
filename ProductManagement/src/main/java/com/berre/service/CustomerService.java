package com.berre.service;

import com.berre.entity.Customer;
import com.berre.repository.CustomerRepository;

public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService() {

        this.customerRepository=new CustomerRepository();
    }

    public void register(Customer customer){
        customerRepository.save(customer);

    }
}
