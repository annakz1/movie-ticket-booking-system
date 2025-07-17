package com.backend.movie_ticket.service;

import com.backend.movie_ticket.model.*;
import com.backend.movie_ticket.repo.BookingRepository;
import com.backend.movie_ticket.repo.MovieRepository;
import com.backend.movie_ticket.repo.ShowtimeRepository;
import com.backend.movie_ticket.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private ShowtimeRepository showtimeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MovieRepository movieRepository;

    public Booking bookTicket(String userEmail, BookingDTO bookingDTO) {
        Showtime showtime = showtimeRepository.findById(bookingDTO.getShowtimeId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Showtime " + bookingDTO.getShowtimeId() + " not found"));

        Movie movie = showtime.getMovie();

        if (bookingRepository.existsByShowtimeAndSeatNumber(showtime, bookingDTO.getSeatNumber())) {
            throw new IllegalArgumentException("Seat already booked for showtime: " + showtime.getId());
        }

        long currentCount = bookingRepository.countByShowtime(showtime); // number of existing bookings for the given showtime
        if (currentCount >= showtime.getMaxSeats()) {
            throw new IllegalArgumentException("All seats are booked for showtime: " + showtime.getId());
        }

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Booking booking = bookingDTO.toBooking(user, movie, showtime);
        return bookingRepository.save(booking);
    }
}

