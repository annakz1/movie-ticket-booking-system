package com.backend.movie_ticket.controller;

import com.backend.movie_ticket.model.Movie;
import com.backend.movie_ticket.model.MovieDTO;
import com.backend.movie_ticket.service.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private EntityManager em;

    @Autowired
    private ObjectMapper om;

    @PostMapping(value = "")
    public ResponseEntity<?> addNewMovie(@RequestBody @Valid MovieDTO movieDTO) {
        Movie movie = movieDTO.toMovie();
        movie = movieService.save(movie);
        return new ResponseEntity<>(movie, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateMovieInfo(@PathVariable Long id, @RequestBody @Valid MovieDTO movieDTO) {
        Optional<Movie> dbMovie = movieService.findById(id);
        if (dbMovie.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie with id: " + id + " not found");
        }
        movieDTO.updateMovieInfo(dbMovie.get());
        Movie updatedMovie = movieService.save(dbMovie.get());
        return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long id) {
        Optional<Movie> dbMovie = movieService.findById(id);
        if (dbMovie.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie with id: " + id + " not found");
        }
        movieService.delete(dbMovie.get());
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getOneMovie(@PathVariable Long id) {
        Optional<Movie> dbMovie = movieService.findById(id);
        if (dbMovie.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie with id: " + id + " not found");
        }
        return new ResponseEntity<>(dbMovie.get(), HttpStatus.OK);
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
