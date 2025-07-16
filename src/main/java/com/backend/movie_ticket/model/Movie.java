package com.backend.movie_ticket.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="movie")
public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String genre;

    private Integer duration;

    private Integer rating;

    @Column(name = "release_year")
    private Integer releaseYear;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    @JsonBackReference // prevents infinite loop
    private List<Showtime> showtimes = new ArrayList<>();
}