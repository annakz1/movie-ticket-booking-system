package com.backend.movie_ticket.repo;

import com.backend.movie_ticket.model.Booking;
import com.backend.movie_ticket.model.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    boolean existsByShowtimeAndSeatNumber(Showtime showtime, Long seatNumber);
    long countByShowtime(Showtime showtime);
}

