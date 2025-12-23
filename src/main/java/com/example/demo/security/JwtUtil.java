package com.example.demo.security;

import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.UserDetails;

@Component
public class JwtUtil {

    public String generateToken(Map<String, Object> claims, String subject) {
        return "dummy-token"; // Replace with real JWT logic later
    }

    public boolean validateToken(String token) {
        return true; // dummy validation
    }

    // Missing method
    public String extractUsername(String token) {
        return "dummy-user"; // return a dummy username for now
    }

    // Overloaded validateToken
    public boolean validateToken(String token, UserDetails userDetails) {
        return true; // dummy, return true for compilation
    }
}
