# Movie Ticket Booking System - Backend Development
A RESTful API for a movie ticket booking system using Spring Boot. The system will manage movies, showtimes, users, and ticket bookings. 

## Prerequisites
maven- apache-maven-3.9.10  
java- 17

## 🚀 Features
This project includes the following key features:
### Movie Management
○	Add new movies with details: title, genre, duration, rating, release_year  
○	Update movie information.   
○	Delete a movie.   
○	Fetch a list of movies or specific movie details.   
### Showtime Management
○	Add showtimes for movies with details: movie, theater, start_time, end_time.   
○	Update showtime details.   
○	Delete a showtime.    
○	Fetch all showtimes for a specific movie or theater.   
●	Constraints:   
○	No overlapping showtimes for the same theater.   
### User Management
○	Register users with details such as name, email, password, and role (Admin/Customer).   
○	Authenticate users via login.   
○	Admin users can manage movies and showtimes.   
○	Customers can book tickets.
### Ticket Booking System
○	Allow customers to book tickets for available showtimes.    
○	Track booking details: user, movie, showtime, seat_number, price.    
○	Ensure no seat is booked twice for the same showtime.    
●	Constraints:    
○	Maximum seats per showtime must be configurable.    