package com.example.demo.service;

import java.util.List;

import com.example.demo.model.User;

public interface UserService {

    User register(User user);
    // AuthResponse login(String email,String password);
    User findById(Long id);
    User findByEmail(String email);
    List<User> findAll();
} 