package com.backend.movie_ticket.service;

import com.backend.movie_ticket.model.Theater;
import com.backend.movie_ticket.repo.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TheaterService {

    @Autowired
    private TheaterRepository theaterRepository;

    public Theater createTheater(Theater theater) {
        return theaterRepository.save(theater);
    }

    public Optional<Theater> getTheater(Long id) {
        return theaterRepository.findById(id);
    }

    public List<Theater> getAllTheaters() {
        return theaterRepository.findAll();
    }

    public void deleteTheater(Long id) {
        theaterRepository.deleteById(id);
    }
}
