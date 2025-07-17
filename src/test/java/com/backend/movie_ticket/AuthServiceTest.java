package com.backend.movie_ticket;

import com.backend.movie_ticket.model.Role;
import com.backend.movie_ticket.model.User;
import com.backend.movie_ticket.repo.UserRepository;
import com.backend.movie_ticket.security.JwtService;
import com.backend.movie_ticket.security.RegisterRequest;
import com.backend.movie_ticket.service.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Test
    void register_shouldEncodePasswordAndGenerateToken() {
        String pass123 = "pass123";
        RegisterRequest request = new RegisterRequest("Test", "test@example.com", pass123, Role.CUSTOMER);
        User user = new User(null, "Test", "test@example.com", "encoded", Role.CUSTOMER);

        when(passwordEncoder.encode(pass123)).thenReturn("encoded");
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(jwtService.generateToken("test@example.com", Role.CUSTOMER)).thenReturn("jwt-token");

        String token = authService.register(request);

        assertEquals("jwt-token", token);
        verify(userRepository).save(any(User.class));
    }
}

