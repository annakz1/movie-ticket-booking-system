package com.backend.movie_ticket.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Movie Booking API",
                version = "1.0",
                description = "API for managing movies, theaters, and showtimes"
        )
)
public class OpenApiConfig {}

