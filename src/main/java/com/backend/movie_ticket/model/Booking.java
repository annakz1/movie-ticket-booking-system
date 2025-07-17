package com.backend.movie_ticket.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "booking", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"showtime_id", "seatNumber"})
})
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    @JsonIgnore
    private Movie movie;

    @ManyToOne
    private Showtime showtime;

    @Column(nullable = false)
    private Long seatNumber;

    private double price;
}


