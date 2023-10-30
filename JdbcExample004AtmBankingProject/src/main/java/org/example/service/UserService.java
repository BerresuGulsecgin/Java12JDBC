package org.example.service;

import org.example.entity.User;
import org.example.repository.UserRepository;

public class UserService {

    private final UserRepository userRepository;
    public UserService() {

        this.userRepository = new UserRepository();
    }

    public User login(String email, String password){
        return userRepository.login(email, password);
    }
}
