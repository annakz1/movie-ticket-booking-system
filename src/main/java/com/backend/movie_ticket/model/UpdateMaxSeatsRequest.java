package com.backend.movie_ticket.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateMaxSeatsRequest {
    @NotNull
    private Long showtimeId;

    @NotNull
    @Min(0)
    private Integer maxSeats;
}
