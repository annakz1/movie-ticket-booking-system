package com.backend.movie_ticket.service;

import com.backend.movie_ticket.model.Movie;
import com.backend.movie_ticket.model.MovieDTO;
import com.backend.movie_ticket.model.Showtime;
import com.backend.movie_ticket.repo.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Movie addNewMovie(MovieDTO movieDTO) {
        Movie movie = movieDTO.toMovie();
        return movieRepository.save(movie);
    }

    public Movie updateMovieInfo(Long id, MovieDTO movieDTO) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie with id: " + id + " not found"));

        movieDTO.updateMovieInfo(movie);
        return movieRepository.save(movie);
    }

    public void deleteMovie(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie with id: " + id + " not found"));

        movieRepository.delete(movie);
    }

    public Movie getOneMovie(Long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie with id: " + id + " not found");
        }
        return movie.get();
    }

    public List<MovieDTO> getFilteredMovies(String title, String genre, Integer duration, Integer rating, Integer year) {
        return movieRepository.findByFilters(title, genre, duration, rating, year).stream()
                .map(this::mapToDTO)
                .toList();
    }


    private MovieDTO mapToDTO(Movie movie) {
        return new MovieDTO(
                movie.getTitle(),
                movie.getGenre(),
                movie.getDuration(),
                movie.getRating(),
                movie.getReleaseYear()
        );
    }

}
