package com.backend.movie_ticket.controller;

import com.backend.movie_ticket.model.Showtime;
import com.backend.movie_ticket.model.ShowtimeDTO;
import com.backend.movie_ticket.model.UpdateMaxSeatsRequest;
import com.backend.movie_ticket.service.ShowtimeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/showtimes")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class ShowtimeController {

    private final ShowtimeService showtimeService;

    @PostMapping
    public ResponseEntity<?> addShowtime(@RequestBody @Valid ShowtimeDTO showtimeDTO) {
        try {
            Showtime showtime = showtimeService.addShowtime(showtimeDTO);
            return new ResponseEntity<>(showtime, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateShowtime(@PathVariable Long id, @RequestBody @Valid ShowtimeDTO showtimeDTO) {
        try {
            Showtime updateShowtime = showtimeService.updateShowtime(id, showtimeDTO);
            return new ResponseEntity<>(updateShowtime, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShowtime(@PathVariable Long id) {
        showtimeService.deleteShowtime(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<Showtime>> getByMovie(@PathVariable Long movieId) {
        return ResponseEntity.ok(showtimeService.getByMovie(movieId));
    }

    @GetMapping("/theater/{theaterId}")
    public ResponseEntity<List<Showtime>> getByTheater(@PathVariable Long theaterId) {
        return ResponseEntity.ok(showtimeService.getByTheater(theaterId));
    }

    @GetMapping("/{id}/max-seats")
    public ResponseEntity<Integer> getMaxSeats(@PathVariable Long id) {
        int maxSeats = showtimeService.getMaxSeats(id);
        return ResponseEntity.ok(maxSeats);
    }

    @PostMapping("/update-max-seats")
    public ResponseEntity<String> updateMaxSeats(@RequestBody @Valid UpdateMaxSeatsRequest request) {
        showtimeService.updateMaxSeats(request.getShowtimeId(), request.getMaxSeats());
        return ResponseEntity.ok("Max seats updated successfully.");
    }


}
