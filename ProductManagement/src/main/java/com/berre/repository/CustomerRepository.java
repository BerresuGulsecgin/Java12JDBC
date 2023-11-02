package com.berre.repository;

import com.berre.entity.Customer;
import com.berre.util.MyRepositoryFactory;

public class CustomerRepository extends MyRepositoryFactory<Customer,Long> {
    public CustomerRepository() {
        super(Customer.class);
    }
}
