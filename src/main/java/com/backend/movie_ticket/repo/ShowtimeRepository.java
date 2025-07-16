package com.backend.movie_ticket.repo;

import com.backend.movie_ticket.model.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {

    List<Showtime> findByMovie_Id(Long id);

    List<Showtime> findByTheater_Id(Long id);

    @Query("""
                SELECT s FROM Showtime s
                WHERE s.theater.id = :theaterId
                  AND (:newStart < s.endTime AND :newEnd > s.startTime)
            """)
    List<Showtime> findOverlappingShowtimes(@Param("theaterId") Long theaterId,
                                            @Param("newStart") LocalDateTime newStart,
                                            @Param("newEnd") LocalDateTime newEnd);
}
