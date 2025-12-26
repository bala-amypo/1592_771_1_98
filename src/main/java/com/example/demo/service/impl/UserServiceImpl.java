// // // package com.example.demo.service.impl;

// // // import java.util.Optional;

// // // import org.springframework.stereotype.Service;

// // // import com.example.demo.entity.User;
// // // import com.example.demo.exception.ResourceNotFoundException;
// // // import com.example.demo.repository.UserRepository;
// // // import com.example.demo.service.UserService;

// // // import jakarta.transaction.Transactional;

// // // @Service
// // // @Transactional
// // // public class UserServiceImpl implements UserService {

// // //     private final UserRepository userRepository;

// // //     public UserServiceImpl(UserRepository userRepository) {
// // //         this.userRepository = userRepository;
// // //     }

// // //     @Override
// // //     public User register(User user) {
// // //         if (userRepository.existsByEmail(user.getEmail())) {
// // //             throw new IllegalArgumentException("Email already registered");
// // //         }
// // //         if (user.getRole() == null) {
// // //             user.setRole("LEARNER");
// // //         }
// // //         return userRepository.save(user);
// // //     }

// // //     @Override
// // //     public User login(String email, String password) {
// // //         if (email == null || password == null) {
// // //             throw new IllegalArgumentException("Email and password must not be null");
// // //         }

// // //         User user = userRepository.findByEmail(email)
// // //                 .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

// // //         if (!user.getPassword().equals(password)) {
// // //             throw new IllegalArgumentException("Invalid email or password");
// // //         }
// // //         return user;
// // //     }

// // //     @Override
// // //     public User findById(Long id) {
// // //         Optional<User> optionalUser = userRepository.findById(id);
// // //         return optionalUser.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
// // //     }

// // //     @Override
// // //     public User findByEmail(String email) {
// // //         Optional<User> optionalUser = userRepository.findByEmail(email);
// // //         return optionalUser.orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
// // //     }
// // // }





// // package com.example.demo.service.impl;

// // import com.example.demo.dto.AuthResponse;
// // import com.example.demo.model.User;
// // import com.example.demo.repository.UserRepository;
// // import com.example.demo.security.JwtUtil;
// // import com.example.demo.service.UserService;
// // import org.springframework.security.crypto.password.PasswordEncoder;
// // import org.springframework.stereotype.Service;
// // import org.springframework.transaction.annotation.Transactional;

// // import java.util.HashMap;

// // @Service
// // @Transactional
// // public class UserServiceImpl implements UserService {

// //     private final UserRepository userRepository;
// //     private final PasswordEncoder passwordEncoder;
// //     private final JwtUtil jwtUtil;

// //     public UserServiceImpl(UserRepository userRepository,
// //                            PasswordEncoder passwordEncoder,
// //                            JwtUtil jwtUtil) {
// //         this.userRepository = userRepository;
// //         this.passwordEncoder = passwordEncoder;
// //         this.jwtUtil = jwtUtil;
// //     }

// //     @Override
// //     public User register(User user) {
// //         if (user == null) throw new RuntimeException("Invalid user");

// //         if (userRepository.existsByEmail(user.getEmail())) {
// //             throw new RuntimeException("Email already exists");
// //         }

// //         user.setPassword(passwordEncoder.encode(user.getPassword()));
// //         if (user.getRole() == null) {
// //             user.setRole("LEARNER");
// //         }

// //         return userRepository.save(user);
// //     }

// //     @Override
// //     public AuthResponse login(String email, String password) {
// //         User user = userRepository.findByEmail(email)
// //                 .orElseThrow(() -> new RuntimeException("User not found"));

// //         if (!passwordEncoder.matches(password, user.getPassword())) {
// //             throw new RuntimeException("Invalid credentials");
// //         }

// //         HashMap<String, Object> claims = new HashMap<>();
// //         claims.put("id", user.getId());
// //         claims.put("role", user.getRole());

// //         String token = jwtUtil.generateToken(claims, user.getEmail());

// //         return AuthResponse.builder()
// //                 .accessToken(token)
// //                 .userId(user.getId())
// //                 .email(user.getEmail())
// //                 .role(user.getRole())
// //                 .message("Login successful")
// //                 .build();
// //     }

// //     @Override
// //     public User findById(Long id) {
// //         return userRepository.findById(id)
// //                 .orElseThrow(() -> new RuntimeException("User not found"));
// //     }

// //     @Override
// //     public User findByEmail(String email) {
// //         return userRepository.findByEmail(email)
// //                 .orElseThrow(() -> new RuntimeException("User not found"));
// //     }
// // }








// package com.example.demo.service.impl;

// import com.example.demo.dto.AuthResponse;
// import com.example.demo.model.User;
// import com.example.demo.repository.UserRepository;
// import com.example.demo.security.JwtUtil;
// import com.example.demo.service.UserService;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;

// @Service
// @Transactional
// public class UserServiceImpl implements UserService {

//     private final UserRepository userRepository;
//     private final PasswordEncoder passwordEncoder;
//     private final JwtUtil jwtUtil;

//     public UserServiceImpl(UserRepository userRepository,
//                            PasswordEncoder passwordEncoder,
//                            JwtUtil jwtUtil) {
//         this.userRepository = userRepository;
//         this.passwordEncoder = passwordEncoder;
//         this.jwtUtil = jwtUtil;
//     }

//     @Override
//     public User register(User user) {
//         if (user == null) {
//             throw new RuntimeException("Invalid user");
//         }

//         if (userRepository.existsByEmail(user.getEmail())) {
//             throw new RuntimeException("Email already exists");
//         }

//         user.setPassword(passwordEncoder.encode(user.getPassword()));

//         if (user.getRole() == null) {
//             user.setRole("LEARNER"); // default role
//         }

//         return userRepository.save(user);
//     }

//     @Override
//     public AuthResponse login(String email, String password) {

//         User user = userRepository.findByEmail(email)
//                 .orElseThrow(() -> new RuntimeException("User not found"));

//         if (!passwordEncoder.matches(password, user.getPassword())) {
//             throw new RuntimeException("Invalid credentials");
//         }

//         // ✅ CORRECT JWT TOKEN GENERATION
//         String token = jwtUtil.generateToken(
//                 user.getEmail(),   // subject
//                 user.getRole()     // role (INSTRUCTOR / ADMIN / LEARNER)
//         );

//         return AuthResponse.builder()
//                 .accessToken(token)
//                 .userId(user.getId())
//                 .email(user.getEmail())
//                 .role(user.getRole())
//                 .message("Login successful")
//                 .build();
//     }

//     @Override
//     public User findById(Long id) {
//         return userRepository.findById(id)
//                 .orElseThrow(() -> new RuntimeException("User not found"));
//     }

//     @Override
//     public User findByEmail(String email) {
//         return userRepository.findByEmail(email)
//                 .orElseThrow(() -> new RuntimeException("User not found"));
//     }
// }







package com.example.demo.service.impl;

import com.example.demo.dto.AuthResponse;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public User register(User user) {
        if (user == null) throw new RuntimeException("Invalid user");

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getRole() == null || user.getRole().isBlank()) {
            user.setRole("LEARNER");
        }

        return userRepository.save(user);
    }

    @Override
    public AuthResponse login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // ✅ Use map-based JWT generation for compatibility with tests
        Map<String, Object> claims = Map.of("role", user.getRole());
        String token = jwtUtil.generateToken(claims, user.getEmail());

        return AuthResponse.builder()
                .accessToken(token)
                .userId(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .message("Login successful")
                .build();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
