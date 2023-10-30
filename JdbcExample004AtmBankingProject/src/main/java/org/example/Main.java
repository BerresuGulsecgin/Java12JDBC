package org.example;


import org.example.entity.User;
import org.example.repository.AccountRepository;
import org.example.repository.UserRepository;
import org.example.service.AccountService;
import org.example.service.UserService;

public class Main {
    public static void main(String[] args) {

        AccountService accountService=new AccountService();
        UserService userService=new UserService();
        User user = userService.login("mehmet@gmail.com", "12345");
        System.out.println("hoşgeldin "+user.getEmail());

        //accountService.createAccount(user);
        //accountService.getAccountByEmail("ahmet@gmail.com");
       accountService.transfer(1000,"1905910924", user);



    }
}