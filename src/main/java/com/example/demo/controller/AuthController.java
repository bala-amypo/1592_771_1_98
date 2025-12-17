package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAll()
    {
        List<User> u=userService.findAll();
        return ResponseEntity.status(200).body(u);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id)
    {
        User u=userService.findById(id);
        return ResponseEntity.status(200).body(u);
    }

    @GetMapping("email/{email}")
    public ResponseEntity<User> getByEmail(@PathVariable String email)
    {
        User u=userService.findByEmail(email);
        return ResponseEntity.status(200).body(u);
    }
}
