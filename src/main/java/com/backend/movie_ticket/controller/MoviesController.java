package com.backend.movie_ticket.controller;

import com.backend.movie_ticket.model.Movie;
import com.backend.movie_ticket.model.MovieDTO;
import com.backend.movie_ticket.service.MoviesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movies")
public class MoviesController {

    @Autowired
    private MoviesService moviesService;

    @Autowired
    private EntityManager em;

    @Autowired
    private ObjectMapper om;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> addNewMovie(@RequestBody @Valid MovieDTO movieDTO) {
        Movie movie = movieDTO.toMovie();
        movie = moviesService.save(movie);
        return new ResponseEntity<>(movieDTO, HttpStatus.CREATED);
    }

}
