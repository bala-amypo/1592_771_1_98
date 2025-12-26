package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /* ------------------------------
     * PASSWORD ENCODER
     * ------------------------------ */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /* ------------------------------
     * SECURITY FILTER CHAIN
     * ------------------------------ */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            /* Disable CSRF for REST APIs */
            .csrf(csrf -> csrf.disable())

            /* Enable CORS */
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))

            /* Stateless session (JWT based) */
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            /* Authorization rules */
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()

                /* Role-based access (future-ready) */
                .requestMatchers("/courses/**").hasAnyRole("INSTRUCTOR", "ADMIN")
                .requestMatchers("/recommendations/**").hasAnyRole("LEARNER", "ADMIN")
                .requestMatchers("/progress/**").hasAnyRole("LEARNER", "ADMIN")

                /* Any other request must be authenticated */
                .anyRequest().authenticated()
            );

        return http.build();
    }

    /* ------------------------------
     * AUTH MANAGER
     * ------------------------------ */
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /* ------------------------------
     * CORS CONFIGURATION
     * ------------------------------ */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(List.of("*"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(false);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }
}


