package com.backend.movie_ticket;

import com.backend.movie_ticket.model.BookingDTO;
import com.backend.movie_ticket.model.Showtime;
import com.backend.movie_ticket.repo.BookingRepository;
import com.backend.movie_ticket.repo.MovieRepository;
import com.backend.movie_ticket.repo.ShowtimeRepository;
import com.backend.movie_ticket.repo.UserRepository;
import com.backend.movie_ticket.service.BookingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {

    @InjectMocks
    private BookingService bookingService;

    @Mock
    private BookingRepository bookingRepository;
    @Mock
    private ShowtimeRepository showtimeRepository;
    @Mock
    private MovieRepository movieRepository;
    @Mock
    private UserRepository userRepository;

    @Test
    void shouldThrowExceptionIfSeatsFull() {
        Long showtimeId = 1L;
        Showtime showtime = new Showtime();
        showtime.setId(showtimeId);
        showtime.setMaxSeats(1);

        when(showtimeRepository.findById(showtimeId)).thenReturn(Optional.of(showtime));
        when(bookingRepository.countByShowtime(showtime)).thenReturn(1L); // already full

        BookingDTO request = new BookingDTO(showtimeId, 5L, 10); // arbitrary seat number

        assertThrows(IllegalArgumentException.class, () -> bookingService.bookTicket("user@example.com", request));
    }
}
