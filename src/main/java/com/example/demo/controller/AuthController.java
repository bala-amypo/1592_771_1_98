package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> createAll(@Valid @RequestBody User user)
    {
        User u=userService.register(user);
        return ResponseEntity.status(201).body(u);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@Valid @RequestBody User user)
    {
        User u=userService.login(user.getEmail(), user.getPassword());
        return ResponseEntity.status(201).body(u);
    }

}
