package com.backend.movie_ticket.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {

    @NotNull
    private Long showtimeId;

    @NotNull
    @Min(1)
    private Long seatNumber;

    private double price;

    public Booking toBooking(User user, Movie movie, Showtime showtime) {
        return Booking.builder().user(user)
                .movie(movie)
                .showtime(showtime)
                .seatNumber(seatNumber)
                .price(price)
                .build();
    }
}

