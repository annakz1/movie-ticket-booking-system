package com.backend.movie_ticket.service;

import com.backend.movie_ticket.model.User;
import com.backend.movie_ticket.repo.UserRepository;
import com.backend.movie_ticket.security.JwtService;
import com.backend.movie_ticket.security.LoginRequest;
import com.backend.movie_ticket.security.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired private JwtService jwtService;
    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    public String register(RegisterRequest request) {
        // check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email is already registered");
        }
        User user = new User(
                null,
                request.getName(),
                request.getEmail(), // Email is used as username
                passwordEncoder.encode(request.getPassword()),
                request.getRole()
        );

        userRepository.save(user);
        return jwtService.generateToken(user.getEmail(), user.getRole()); // email is used as username
    }

    public String login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()
                )
        );
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Invalid credentials"));
        return jwtService.generateToken(user.getEmail(), user.getRole()); // email is used as username
    }
}

