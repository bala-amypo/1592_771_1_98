// package com.example.demo.controller;

// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.example.demo.entity.User;
// import com.example.demo.service.UserService;

// import io.swagger.v3.oas.annotations.tags.Tag;
// import jakarta.validation.Valid;

// @RestController
// @RequestMapping("/auth")
// @Tag(name = "Authentication")
// public class AuthController {

//     private final UserService userService;

//     public AuthController(UserService userService) {
//         this.userService = userService;
//     }

//     @PostMapping("/register")
//     public ResponseEntity<User> createAll(@Valid @RequestBody User user) {
//         User u = userService.register(user);
//         return ResponseEntity.status(201).body(u);
//     }

//     @PostMapping("/login/{email}/{password}")
//     public ResponseEntity<User> login(@PathVariable String email,@PathVariable String password) {
//         User u = userService.login(email,password);
//         return ResponseEntity.status(201).body(u);
//     }

// }



package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.example.demo.model.User;
import com.example.demo.dto.AuthRequest;
import com.example.demo.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserServiceImpl service;

    @Autowired
    public AuthController(UserServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        User newUser = service.register(user);
        return ResponseEntity.ok(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
        User user = service.login(req.getEmail(), req.getPassword());
        return ResponseEntity.ok(user);
    }
}
