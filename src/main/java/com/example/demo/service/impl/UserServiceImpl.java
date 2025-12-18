package com.example.demo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    UserRepository userRepository;
    public User register(User user)
    {
        if(userRepository.existsByEmail(user.getEmail()))
        {
            throw new IllegalArgumentException("Email already registered");
        }
        if(user.getRole()==null)
        {
            user.setRole("LEARNER");
        }
        return userRepository.save(user);
    }

    public User login(String email,String password)
    {
        if(email==null || password==null)
        {
            throw new IllegalArgumentException("Email and password must not be null");
        }

        User user=userRepository.findByEmail(email)
        .orElseThrow(()->new IllegalArgumentException("Invalid email or password"));

        if(!user.getPassword().equals(password))
        {
            throw new IllegalArgumentException("Invalid email or password");
        }
        return user;
    }
    public User findById(Long id)
    {
        Optional<User> optionalUser=userRepository.findById(id);
        return optionalUser.orElseThrow(()->new IllegalArgumentException("User not found with id: "+ id));
    }

    public User findByEmail(String email)
    {
        Optional<User> optionalUser=userRepository.findByEmail(email);
        return optionalUser.orElseThrow(()->new IllegalArgumentException("User not found with email: "+ email));
    }

    
}