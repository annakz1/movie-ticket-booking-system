package com.backend.movie_ticket.security;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
