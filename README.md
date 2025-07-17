## Movie Ticket Booking System - Backend    
A RESTful API for a movie ticket booking system built with Spring Boot. This backend manages movies, showtimes, user authentication, and ticket bookings with role-based access control.

🔧 Prerequisites
- Java: 17
- Maven: Apache Maven 3.9.10
- Docker: Installed and running

🚀 Getting Started  
🛠️ Build & Run    
``` 
mvn clean install -U
docker build -t movie-ticket-booking-system .        
docker-compose up 
 ```
  
Once up, access the API via Swagger UI:  
➡️ http://localhost:8080/swagger-ui/index.html

📘 API Documentation
- API definition is available under the /docs directory as openapi.yaml.
- You can load it in Swagger Editor or any Swagger UI.

🔐 Using JWT for Authentication  
Steps:  
1. <b> Login </b>   
Send a POST request to /api/auth/login with email and password.
2. <b> Receive JWT Token </b>  
The token will be included in the response.
3. <b> Authorize Requests </b>  
For protected endpoints (e.g. /api/admin/movies), include the token in the request header:
`Authorization: Bearer <your-jwt-token>`

💡 Features    
🎥 Movie Management    
- Add new movies with: title, genre, duration, rating, release_year  
- Update existing movie details
- Delete movies   
- Retrieve all or specific movie details   

🕒 Showtime Management
- Create showtimes: movie, theater, start_time, end_time   
- Update and delete showtimes  
- Fetch showtimes by movie or theater   
<b> Constraints: </b>  
- No overlapping showtimes in the same theater

👥 User Management     
- Register new users: name, email, password, role (ADMIN or CUSTOMER)   
- Login with email and password
- Admins can manage movies and showtimes   
- Customers can book tickets

🎫 Ticket Booking   
- Book tickets for available showtimes   
- Track: user, movie, showtime, seat_number, price 
- Prevent duplicate seat bookings per showtime   
<b> Constraints: </b>
- Maximum seats per showtime are configurable   