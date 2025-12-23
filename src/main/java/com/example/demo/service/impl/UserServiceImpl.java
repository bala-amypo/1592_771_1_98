// package com.example.demo.service.impl;

// import java.util.Optional;

// import org.springframework.stereotype.Service;

// import com.example.demo.entity.User;
// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.repository.UserRepository;
// import com.example.demo.service.UserService;

// import jakarta.transaction.Transactional;

// @Service
// @Transactional
// public class UserServiceImpl implements UserService {

//     private final UserRepository userRepository;

//     public UserServiceImpl(UserRepository userRepository) {
//         this.userRepository = userRepository;
//     }

//     @Override
//     public User register(User user) {
//         if (userRepository.existsByEmail(user.getEmail())) {
//             throw new IllegalArgumentException("Email already registered");
//         }
//         if (user.getRole() == null) {
//             user.setRole("LEARNER");
//         }
//         return userRepository.save(user);
//     }

//     @Override
//     public User login(String email, String password) {
//         if (email == null || password == null) {
//             throw new IllegalArgumentException("Email and password must not be null");
//         }

//         User user = userRepository.findByEmail(email)
//                 .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

//         if (!user.getPassword().equals(password)) {
//             throw new IllegalArgumentException("Invalid email or password");
//         }
//         return user;
//     }

//     @Override
//     public User findById(Long id) {
//         Optional<User> optionalUser = userRepository.findById(id);
//         return optionalUser.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
//     }

//     @Override
//     public User findByEmail(String email) {
//         Optional<User> optionalUser = userRepository.findByEmail(email);
//         return optionalUser.orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
//     }
// }



package com.example.demo.service.impl;

import com.example.demo.dto.AuthResponse;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;

public class UserServiceImpl {

    private final UserRepository repo;
    private final BCryptPasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public UserServiceImpl(UserRepository r, BCryptPasswordEncoder e, JwtUtil j) {
        this.repo = r;
        this.encoder = e;
        this.jwtUtil = j;
    }

    public User register(User user) {
        if (user == null) throw new RuntimeException();
        if (repo.existsByEmail(user.getEmail())) throw new RuntimeException();
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public AuthResponse login(String email, String password) {
        User u = repo.findByEmail(email).orElseThrow(RuntimeException::new);
        if (!encoder.matches(password, u.getPassword())) throw new RuntimeException();
        return new AuthResponse(jwtUtil.generateToken(new HashMap<>(), email));
    }

    public User findByEmail(String email) {
        return repo.findByEmail(email).orElse(null);
    }
}
