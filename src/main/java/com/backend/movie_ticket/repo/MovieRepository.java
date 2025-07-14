package com.backend.movie_ticket.repo;

import com.backend.movie_ticket.model.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie,Long> {


}
