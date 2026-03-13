package org.example.kinograf.config;

import org.example.kinograf.model.*;
import org.example.kinograf.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class InitApp {

    @Bean
    CommandLineRunner initDatabase(MovieRepository movieRepository,
                                   ReservationRepository reservationRepository,
                                   ShowtimeRepository showtimeRepository,
                                   TheatreRepository theatreRepository,
                                   TicketRepository ticketRepository,
                                   SeatRepository seatRepository) {

        return args -> {
            Theatre theatre = new Theatre();
            theatre.setTheatreName("Opera");
            theatre.setCapacity(25);
            theatre.setRowsInTheatre(20);
            theatre.setSeatsInTheatre(12);
            theatre = theatreRepository.save(theatre);

            Movie movie1 = new Movie();
            movie1.setName("Inception");
            movie1.setOmdbID("tt1375666");

            Movie movie2 = new Movie();
            movie2.setName("The Dark Knight");
            movie2.setOmdbID("tt0468569");

            Movie movie3 = new Movie();
            movie3.setName("Interstellar");
            movie3.setOmdbID("tt0816692");

            movie1 = movieRepository.save(movie1);
            movie2 = movieRepository.save(movie2);
            movie3 = movieRepository.save(movie3);

            Reservation r1 = new Reservation();
            r1.setCustomerName("John Doe");
            r1.setPhoneNumber("12345678");

            Reservation r2 = new Reservation();
            r2.setCustomerName("Jane Smith");
            r2.setPhoneNumber("87654321");

            Reservation r3 = new Reservation();
            r3.setCustomerName("Peter Parker");
            r3.setPhoneNumber("11223344");

            reservationRepository.save(r1);
            reservationRepository.save(r2);
            reservationRepository.save(r3);

            ShowTimes st1 = new ShowTimes();
            st1.setMovie(movie1);
            st1.setTheatre(theatre);
            st1.setTimeOfDay(LocalDateTime.now().plusDays(1).withHour(18).withMinute(30));
            showtimeRepository.save(st1);

            ShowTimes st2 = new ShowTimes();
            st2.setMovie(movie2);
            st2.setTheatre(theatre);
            st2.setTimeOfDay(LocalDateTime.now().plusDays(1).withHour(21).withMinute(0));
            showtimeRepository.save(st2);

            ShowTimes st3 = new ShowTimes();
            st3.setMovie(movie3);
            st3.setTheatre(theatre);
            st3.setTimeOfDay(LocalDateTime.now().plusDays(2).withHour(19).withMinute(15));
            showtimeRepository.save(st3);

            Seat seat1 = new Seat();
            seat1.setSeatNumber(5);
            seat1.setSeatRow(5);
            seat1.setTheatre(theatre);
            seatRepository.save(seat1);

            Ticket ticket = new Ticket();
            ticket.setPrice(100);
            ticket.setReservation(r1);
            ticket.setSeat(seat1);
            ticket.setShowTimes(st1);
            ticketRepository.save(ticket);
        };
    }
}