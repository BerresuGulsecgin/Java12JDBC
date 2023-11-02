package com.berre.controller;

import com.berre.entity.Customer;
import com.berre.entity.Information;
import com.berre.service.CustomerService;
import com.berre.util.BAUtils;

public class CustomerController {

    private final CustomerService customerService;

    public CustomerController() {
        this.customerService=new CustomerService();
    }

    public void register(){
        String firstname= BAUtils.readString("isminiz");
        String lastname= BAUtils.readString("soyisminiz");
        String email= BAUtils.readString("emailiniz");

        Information information=Information.builder()
                .firstname(firstname)
                .lastname(lastname)
                .email(email)
                .build();

        String password= BAUtils.readString("şifrenz");
        String identityNo= BAUtils.readString("tcniz");

        Customer customer=Customer.builder()
                .identity(identityNo)
                .password(password)
                .information(information)
                .build();

        customerService.register(customer);
    }
}
