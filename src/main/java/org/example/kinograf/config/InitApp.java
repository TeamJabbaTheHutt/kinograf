package org.example.kinograf.config;

import org.example.kinograf.model.*;
import org.example.kinograf.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Configuration
public class InitApp {

    @Bean
    CommandLineRunner initDatabase(MovieRepository movieRepository,
                                   ReservationRepository reservationRepository, ShowtimeRepository showtimeRepository,
                                   TheatreRepository theatreRepository,
                                   TicketRepository ticketRepository,
                                   SeatRepository seatRepository) {

        return args -> {
            Theatre theatre = new Theatre();
            theatre.setTheatreName("Opera");
            theatre.setCapacity(25);
            theatre.setRowsInTheatre(20); // rows y
            theatre.setSeatsInTheatre(12); // columns x
            theatre = theatreRepository.save(theatre);

            Movie movie1 = new Movie();
            movie1.setName("Dune");
            movie1.setCategories("Sci-Fi");
            movie1.setAgeLimit(15);

            Movie movie2 = new Movie();
            movie2.setName("Batman");
            movie2.setCategories("Action");
            movie2.setAgeLimit(13);

            Movie movie3 = new Movie();
            movie3.setName("Frozen");
            movie3.setCategories("Animation");
            movie3.setAgeLimit(7);

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

            r1 = reservationRepository.save(r1);
            r2 = reservationRepository.save(r2);
            r3 = reservationRepository.save(r3);




            ShowTimes st1 = new ShowTimes();
            st1.setMovie(movie1);
            st1.setTheatre(theatre);
            st1.setTimeOfDay(LocalDate.now());
            st1 = showtimeRepository.save(st1);

            ShowTimes st2 = new ShowTimes();
            st2.setMovie(movie1);
            st2.setTheatre(theatre);
            st2.setTimeOfDay(LocalDate.now());
            st2 = showtimeRepository.save(st2);

            ShowTimes st3 = new ShowTimes();
            st3.setMovie(movie2);
            st3.setTheatre(theatre);
            st3.setTimeOfDay(LocalDate.now());
            st3 = showtimeRepository.save(st3);

            Seat seat1 = new Seat();
            seat1.setSeatNumber(5);
            seat1.setSeatRow(5);
            seat1.setTheatre(theatre);
            seat1 = seatRepository.save(seat1);




            Ticket ticket = new Ticket();
            ticket.setPrice(100);
            ticket.setReservation(r1);
            ticket.setSeat(seat1);
            ticket.setShowTimes(st1);
            ticketRepository.save(ticket);
        };
    }
}

