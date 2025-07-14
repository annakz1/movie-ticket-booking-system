FROM openjdk:17
COPY target/movie-ticket-booking-system*.jar /usr/src/movie-ticket-booking-system.jar
COPY src/main/resources/application.properties /opt/conf/application.properties
CMD ["java", "-jar", "/usr/src/movie-ticket-booking-system.jar", "--spring.config.location=file:/opt/conf/application.properties"]
