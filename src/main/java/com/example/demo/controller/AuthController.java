// // package com.example.demo.controller;

// // import org.springframework.http.ResponseEntity;
// // import org.springframework.web.bind.annotation.PathVariable;
// // import org.springframework.web.bind.annotation.PostMapping;
// // import org.springframework.web.bind.annotation.RequestBody;
// // import org.springframework.web.bind.annotation.RequestMapping;
// // import org.springframework.web.bind.annotation.RestController;

// // import com.example.demo.entity.User;
// // import com.example.demo.service.UserService;

// // import io.swagger.v3.oas.annotations.tags.Tag;
// // import jakarta.validation.Valid;

// // @RestController
// // @RequestMapping("/auth")
// // @Tag(name = "Authentication")
// // public class AuthController {

// //     private final UserService userService;

// //     public AuthController(UserService userService) {
// //         this.userService = userService;
// //     }

// //     @PostMapping("/register")
// //     public ResponseEntity<User> createAll(@Valid @RequestBody User user) {
// //         User u = userService.register(user);
// //         return ResponseEntity.status(201).body(u);
// //     }

// //     @PostMapping("/login/{email}/{password}")
// //     public ResponseEntity<User> login(@PathVariable String email,@PathVariable String password) {
// //         User u = userService.login(email,password);
// //         return ResponseEntity.status(201).body(u);
// //     }

// // }




// package com.example.demo.controller;

// import com.example.demo.dto.AuthResponse;
// import com.example.demo.dto.LoginRequest;
// import com.example.demo.model.User;
// import com.example.demo.security.JwtUtil;
// import com.example.demo.service.UserService;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/auth")
// @Tag(name = "Authentication")
// public class AuthController {

//     private final UserService userService;
//     private final JwtUtil jwtUtil;

//     public AuthController(UserService userService, JwtUtil jwtUtil) {
//         this.userService = userService;
//         this.jwtUtil = jwtUtil;
//     }

//     @PostMapping("/register")
//     public ResponseEntity<User> register(@RequestBody User user) {
//         return ResponseEntity.ok(userService.register(user));
//     }

//     @PostMapping("/login")
//     public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
//         AuthResponse response =
//                 userService.login(request.getEmail(), request.getPassword());
//         return ResponseEntity.ok(response);
//     }
// }











package com.example.demo.controller;

import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.model.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        AuthResponse response =
                userService.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(response);
    }
}
