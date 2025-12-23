// package com.example.demo.repository;

// import java.util.Optional;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// import com.example.demo.entity.User;

// @Repository
// public interface UserRepository extends JpaRepository<User, Long> {
//     boolean existsByEmail(String email);

//     Optional<User> findByEmail(String email);
// }





package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username); // this is required in CustomUserDetailsService
}
