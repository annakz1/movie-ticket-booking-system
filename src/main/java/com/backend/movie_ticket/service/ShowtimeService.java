package com.backend.movie_ticket.service;

import com.backend.movie_ticket.model.Movie;
import com.backend.movie_ticket.model.Showtime;
import com.backend.movie_ticket.model.ShowtimeDTO;
import com.backend.movie_ticket.model.Theater;
import com.backend.movie_ticket.repo.MovieRepository;
import com.backend.movie_ticket.repo.ShowtimeRepository;
import com.backend.movie_ticket.repo.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ShowtimeService {

    @Autowired
    private ShowtimeRepository showtimeRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private TheaterRepository theaterRepository;

    public Showtime addShowtime(ShowtimeDTO showtimeDTO) {
        if (showtimeDTO.getEndTime().isBefore(showtimeDTO.getStartTime())) {
            throw new IllegalArgumentException("Showtime end-time is before Showtime start-time");
        }
        Movie movie = movieRepository.findById(showtimeDTO.getMovieId())
                .orElseThrow(() -> new IllegalArgumentException("Movie " + showtimeDTO.getMovieId() + " not found"));
        Theater theater = theaterRepository.findById(showtimeDTO.getTheaterId())
                .orElseGet(() -> {
                    Theater newTheater = theaterRepository.save(new Theater()); // Save it to get an ID
                    newTheater.setName("Theater_" + newTheater.getId());
                    return newTheater;
                });

        validateOverlap(showtimeDTO);

        Showtime showtime = showtimeDTO.toShowtime(movie, theater);
        return showtimeRepository.save(showtime);
    }

    public List<Showtime> getByMovie(Long movieId) {
        if (!movieRepository.existsById(movieId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie with ID " + movieId + " not found");
        }
        return showtimeRepository.findByMovie_Id(movieId);
    }

    public List<Showtime> getByTheater(Long theaterId) {
        if (!theaterRepository.existsById(theaterId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Theater with ID " + theaterId + " not found");
        }
        return showtimeRepository.findByTheater_Id(theaterId);
    }

    public void deleteShowtime(Long id) {
        showtimeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Showtime " + id + " not found"));

        showtimeRepository.deleteById(id);
    }

    public Showtime updateShowtime(Long id, ShowtimeDTO showtimeDTO) {
        Showtime showtime = showtimeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Showtime " + id + " not found"));

        if (showtimeDTO.getEndTime().isBefore(showtimeDTO.getStartTime())) {
            throw new IllegalArgumentException("Showtime end-time is before Showtime start-time");
        }
        validateOverlap(showtimeDTO);

        Movie movie = movieRepository.findById(showtimeDTO.getMovieId())
                .orElseThrow(() -> new IllegalArgumentException("Movie " + showtimeDTO.getMovieId() + " not found"));
        Theater theater = theaterRepository.findById(showtimeDTO.getTheaterId())
                .orElseGet(() -> {
                    Theater newTheater = theaterRepository.save(new Theater()); // Save it to get an ID
                    newTheater.setName("Theater_" + newTheater.getId());
                    return newTheater;
                });

        showtimeDTO.updateShowtime(showtime, movie, theater);

        return showtimeRepository.save(showtime);
    }

    private void validateOverlap(ShowtimeDTO showtimeDTO) {
        List<Showtime> overlapping = showtimeRepository.findOverlappingShowtimes(
                showtimeDTO.getTheaterId(),
                showtimeDTO.getStartTime(),
                showtimeDTO.getEndTime()
        );
        if (!overlapping.isEmpty()) {
            throw new IllegalArgumentException("Overlapping showtime already exists for this theater.");
        }
    }

    public int getMaxSeats(Long showtimeId) {
        Showtime showtime = showtimeRepository.findById(showtimeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Showtime " + showtimeId + " not found"));

        return showtime.getMaxSeats();
    }

    public void updateMaxSeats(Long showtimeId, int maxSeats) {
        Showtime showtime = showtimeRepository.findById(showtimeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Showtime " + showtimeId + " not found"));

        showtime.setMaxSeats(maxSeats);
        showtimeRepository.save(showtime);
    }

}

