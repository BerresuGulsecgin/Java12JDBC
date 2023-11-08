package com.berre.controller;

import com.berre.entity.Customer;
import com.berre.entity.Information;
import com.berre.entity.enums.AccountType;
import com.berre.service.CustomerService;
import com.berre.util.BAUtils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;

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
                .accountType(AccountType.NORMAL)
                .build();

        customerService.register(customer);
    }

    public Customer login() {
        String identity=BAUtils.readString("tcnizi giriniz");
        String password= BAUtils.readString("şifrenizi giriniz");

        return customerService.findCustomerByIdentity(identity,password).get();

    }



    public void upgradeAccount(Customer customer) {


        HashMap<Integer, String> menuItems=new HashMap<>(Arrays.stream(AccountType.values()).collect(Collectors.toMap(Enum::ordinal, Enum::name)));
        int index=BAUtils.menu(menuItems);
        AccountType accountType=AccountType.values()[index];
        customer.setAccountType(accountType);

        customerService.upgradeAccount(customer);
    }
}
