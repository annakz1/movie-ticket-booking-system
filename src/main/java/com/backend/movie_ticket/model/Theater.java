package com.backend.movie_ticket.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "theater")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String location;

    // Optional: total number of seats
    private Integer totalSeats;

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Showtime> showtimes = new ArrayList<>();
}
