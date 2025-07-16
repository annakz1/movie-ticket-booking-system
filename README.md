# Movie Ticket Booking System - Backend Development
A RESTful API for a movie ticket booking system using Spring Boot. The system will manage movies, showtimes, users, and ticket bookings. 

### Prerequisites
maven- apache-maven-3.9.10  
java- 17
docker

### Steps to run the application
mvn clean install -U  
docker build -t movie-ticket-booking-system .  
docker-compose up  
And then you can access the API from swagger:  
http://localhost:8080/swagger-ui/index.html  

##### How to use JWT token after login:
1. Login: You send a login request to /api/auth/login with email & password.
2. Receive JWT token in the response 
3. For any protected endpoint (like /api/admin/movies), you need to include the JWT token in the Authorization header as a Bearer token:
   Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...

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
