package com.backend.movie_ticket.service;

import com.backend.movie_ticket.model.Movie;
import com.backend.movie_ticket.repo.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoviesService {

    @Autowired
    private MovieRepository repository;

    public Movie save(Movie movie) {
        return repository.save(movie);
    }

}
