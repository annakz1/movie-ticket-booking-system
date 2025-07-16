package com.backend.movie_ticket.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShowtimeDTO {

    @NotNull
    private Long movieId;

    @NotNull
    private Long theaterId;

    @NotNull
    private LocalDateTime startTime;

    @NotNull
    private LocalDateTime endTime;

    public void updateShowtime(Showtime showtime, Movie movie, Theater theater) {
        showtime.setMovie(movie);
        showtime.setTheater(theater);
        showtime.setStartTime(startTime);
        showtime.setEndTime(endTime);
    }
}
