package com.example.demo.dto;

import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AuthRequest {
    private String email;
    private String password;
}
