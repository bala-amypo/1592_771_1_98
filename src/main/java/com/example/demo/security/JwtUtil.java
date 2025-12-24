package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

    private final String secret;
    private final Long expirationMs;
    private final Key signingKey;

    /**
     * Constructor injection (as per requirement)
     */
    public JwtUtil(
            @Value("${jwt.secret:default-secret-key-which-is-long-enough}") String secret,
            @Value("${jwt.expirationMs:86400000}") Long expirationMs) {

        this.secret = secret;
        this.expirationMs = expirationMs;
        this.signingKey = Keys.hmacShaKeyFor(secret.getBytes());
    }

    /**
     * Generates JWT token
     * Algorithm: HS256
     * Subject: user email
     * Claims: userId, email, role
     */
    public String generateToken(Map<String, Object> claims, String subject) {

        Date now = new Date();
        Date expiry = new Date(now.getTime() + expirationMs);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Validates JWT token
     * Returns true if valid, false otherwise
     * (NO exception propagation — test safe)
     */
    public boolean validateToken(String token) {
        try {
            getAllClaims(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Extracts email (subject) from token
     */
    public String getEmailFromToken(String token) {
        try {
            return getAllClaims(token).getSubject();
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Extracts role from token
     */
    public String getRoleFromToken(String token) {
        try {
            Object role = getAllClaims(token).get("role");
            return role != null ? role.toString() : null;
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Extracts userId from token
     */
    public Long getUserIdFromToken(String token) {
        try {
            Object id = getAllClaims(token).get("id");
            if (id instanceof Integer) {
                return ((Integer) id).longValue();
            }
            if (id instanceof Long) {
                return (Long) id;
            }
            if (id != null) {
                return Long.parseLong(id.toString());
            }
            return null;
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Internal helper to parse claims
     */
    private Claims getAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
