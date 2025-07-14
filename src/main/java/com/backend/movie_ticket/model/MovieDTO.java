package com.backend.movie_ticket.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Data
public class MovieDTO implements Serializable {

    @NotBlank
    @Size(max = 60)
    private String title;

    @Size(max = 60)
    private String genre;

    @Min(0)
    @Max(800)
    private Integer duration;

    @Min(0)
    @Max(10)
    private Double rating;

    @Size(max = 60)
    private String releaseYear;

    public Movie toMovie() {
        return Movie.builder().title(title).genre(genre)
                .duration(duration).rating(rating)
                .releaseYear(releaseYear)
                .build();
    }

}