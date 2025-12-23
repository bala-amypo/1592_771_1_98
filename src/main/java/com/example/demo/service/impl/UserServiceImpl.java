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

import org.springframework.stereotype.Service;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import java.util.Optional;

@Service
public class UserServiceImpl {

    private final UserRepository repo;

    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    // Register a new user
    public User register(User user) {
        if(repo.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        return repo.save(user);
    }

    // Login user
    public User login(String email, String password) {
        User user = repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if(!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }
        return user;
    }
}
