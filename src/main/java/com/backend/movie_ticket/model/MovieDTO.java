package com.backend.movie_ticket.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO implements Serializable {

    @NotBlank
    @Size(max=60)
    private String title;

    @Size(max=60)
    private String genre;

    @Min(0)
    @Max(800)
    private Integer duration;

    @Min(0)
    @Max(10)
    private Integer rating;

    @Min(1900)
    @Max(2100)
    private Integer releaseYear;

    public Movie toMovie() {
        return Movie.builder().title(title)
                .genre(genre)
                .duration(duration)
                .rating(rating)
                .releaseYear(releaseYear)
                .build();
    }

    public void updateMovieInfo(Movie movie) {
        movie.setTitle(title);
        movie.setGenre(genre);
        movie.setDuration(duration);
        movie.setRating(rating);
        movie.setReleaseYear(releaseYear);
    }
}