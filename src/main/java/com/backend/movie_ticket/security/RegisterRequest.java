package com.backend.movie_ticket.security;

import com.backend.movie_ticket.model.Role;
import lombok.Data;

@Data
public class RegisterRequest {

    private String name;
    private String email;
    private String password;
    private Role role;
}