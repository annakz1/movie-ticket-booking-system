package com.backend.movie_ticket;

import com.backend.movie_ticket.model.BookingDTO;
import com.backend.movie_ticket.model.Movie;
import com.backend.movie_ticket.model.Showtime;
import com.backend.movie_ticket.repo.MovieRepository;
import com.backend.movie_ticket.repo.ShowtimeRepository;
import com.backend.movie_ticket.security.JwtAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
public class BookingIntegrationTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private MovieRepository movieRepository;
    @Autowired private ShowtimeRepository showtimeRepository;

    @MockBean
    private JwtAuthenticationFilter jwtFilter;

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15")
            .withDatabaseName("moviedb")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void overrideProps(DynamicPropertyRegistry registry) {
        postgres.start();
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    private Long movieId;
    private Long showtimeId;

    @BeforeEach
    void setup() {
        // Create and save a movie
        Movie movie = new Movie();
        movie.setTitle("Test Movie");
        movie.setGenre("Drama");
        movie.setDuration(120);
        movie.setReleaseYear(2024);
        movie = movieRepository.save(movie);
        movieId = movie.getId();

        // Create and save a showtime
        Showtime showtime = new Showtime();
        showtime.setMovie(movie);
        showtime.setStartTime(LocalDateTime.now());
        showtime.setEndTime(LocalDateTime.now().plusDays(1));
        showtime.setMaxSeats(100);
        showtime = showtimeRepository.save(showtime);
        showtimeId = showtime.getId();  // Save the generated ID
    }

    @Test
    @WithMockUser(username = "customer@example.com", roles = {"CUSTOMER"})
    void customerCanBookTicket_whenSeatsAvailable() throws Exception {
        BookingDTO request = new BookingDTO(movieId, showtimeId, 0);

        mockMvc.perform(post("/api/customer/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }
}

