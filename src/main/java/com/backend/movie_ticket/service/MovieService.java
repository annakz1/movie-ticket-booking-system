package com.backend.movie_ticket.service;

import com.backend.movie_ticket.model.Movie;
import com.backend.movie_ticket.model.MovieDTO;
import com.backend.movie_ticket.repo.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Optional<Movie> findById(Long id) {
        return movieRepository.findById(id);
    }

    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    public void delete(Movie movie) {
        movieRepository.delete(movie);
    }

    public List<MovieDTO> getAllMovies() {
        return movieRepository.findAll().stream()
                .map(this::mapToDTO)
                .toList();
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
