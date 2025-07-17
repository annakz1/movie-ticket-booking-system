package com.backend.movie_ticket.controller;

import com.backend.movie_ticket.model.Movie;
import com.backend.movie_ticket.model.MovieDTO;
import com.backend.movie_ticket.service.MovieService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/movies")
@SecurityRequirement(name = "bearerAuth")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<?> addNewMovie(@RequestBody @Valid MovieDTO movieDTO) {
        try {
            Movie movie = movieService.addNewMovie(movieDTO);
            return new ResponseEntity<>(movie, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateMovieInfo(@PathVariable Long id, @RequestBody @Valid MovieDTO movieDTO) {
        try {
            Movie updateShowtime = movieService.updateMovieInfo(id, movieDTO);
            return new ResponseEntity<>(updateShowtime, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getOneMovie(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getOneMovie(id));
    }

    @GetMapping
    public ResponseEntity<List<MovieDTO>> getFilteredMovies(
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Integer duration,
            @RequestParam(required = false) Integer rating,
            @RequestParam(required = false) Integer year) {
        List<MovieDTO> movies = movieService.getFilteredMovies(title, genre, duration, rating, year);
        return ResponseEntity.ok(movies);
    }

}
