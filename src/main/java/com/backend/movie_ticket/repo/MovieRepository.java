package com.backend.movie_ticket.repo;

import com.backend.movie_ticket.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    // TODO add LOWER
    @Query("SELECT m FROM Movie m WHERE " +
            "(:title IS NULL OR m.title = :title) AND " +
            "(:genre IS NULL OR m.genre = :genre) AND " +
            "(:duration IS NULL OR m.duration = :duration) AND " +
            "(:rating IS NULL OR m.rating = :rating) AND " +
            "(:year IS NULL OR m.releaseYear = :year)")
    List<Movie> findByFilters(
            @Param("title") String title,
            @Param("genre") String genre,
            @Param("duration") Integer duration,
            @Param("rating") Integer rating,
            @Param("year") Integer year
    );


}
