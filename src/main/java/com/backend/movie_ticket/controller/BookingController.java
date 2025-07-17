package com.backend.movie_ticket.controller;

import com.backend.movie_ticket.model.Booking;
import com.backend.movie_ticket.model.BookingDTO;
import com.backend.movie_ticket.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer/bookings")
@RequiredArgsConstructor
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<?> bookTicket(@RequestBody @Valid BookingDTO request,
                                        @AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername(); // JWT holds email as username
        Booking booking = bookingService.bookTicket(email, request);
        return new ResponseEntity<>(booking, HttpStatus.CREATED);
    }
}

