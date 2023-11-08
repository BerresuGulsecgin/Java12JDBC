package com.berre.service;

import com.berre.entity.Customer;
import com.berre.entity.enums.AccountType;
import com.berre.repository.CustomerRepository;

import javax.persistence.NoResultException;
import java.util.Optional;

public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService() {

        this.customerRepository=new CustomerRepository();
    }

    public void register(Customer customer){
        customerRepository.save(customer);

    }

    public Optional<Customer> findCustomerByIdentity(String identity,String password) {

        Optional<Customer> optionalCustomer= customerRepository.findCustomerByIdentity(identity);
        if (optionalCustomer.isPresent()){
            if (optionalCustomer.get().getPassword().equals(password)){
                return optionalCustomer;
            }else {
                System.out.println("Password hatalı");
            }
        }else {
            System.out.println("Customer bulunamadı!");
        }
        return Optional.empty();


    }


    public void upgradeAccount(Customer customer) {

        customerRepository.update(customer);
    }
}
