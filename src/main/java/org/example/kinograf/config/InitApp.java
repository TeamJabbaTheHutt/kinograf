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
            if (movieRepository.count() > 0) {
                return;
            }

            Theatre theatre = new Theatre();
            theatre.setTheatreName("Opera");
            theatre.setRowsInTheatre(10);
            theatre.setSeatsInTheatre(12);
            theatre.setCapacity(10 * 12);
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

            r1 = reservationRepository.save(r1);
            r2 = reservationRepository.save(r2);
            r3 = reservationRepository.save(r3);

            ShowTimes st1 = new ShowTimes();
            st1.setMovie(movie1);
            st1.setTheatre(theatre);
            st1.setTimeOfDay(LocalDateTime.now().plusDays(1).withHour(18).withMinute(30));
            st1 = showtimeRepository.save(st1);

            ShowTimes st2 = new ShowTimes();
            st2.setMovie(movie2);
            st2.setTheatre(theatre);
            st2.setTimeOfDay(LocalDateTime.now().plusDays(1).withHour(21).withMinute(0));
            st2 = showtimeRepository.save(st2);

            ShowTimes st3 = new ShowTimes();
            st3.setMovie(movie3);
            st3.setTheatre(theatre);
            st3.setTimeOfDay(LocalDateTime.now().plusDays(2).withHour(19).withMinute(15));
            st3 = showtimeRepository.save(st3);

            Seat seat1 = new Seat();
            seat1.setSeatNumber(5);
            seat1.setSeatRow(5);
            seat1.setTheatre(theatre);
            seat1 = seatRepository.save(seat1);

            Seat seat2 = new Seat();
            seat2.setSeatNumber(1);
            seat2.setSeatRow(1);
            seat2.setTheatre(theatre);
            seat2 = seatRepository.save(seat2);

            Seat seat3 = new Seat();
            seat3.setSeatNumber(2);
            seat3.setSeatRow(1);
            seat3.setTheatre(theatre);
            seat3 = seatRepository.save(seat3);

            Seat seat4 = new Seat();
            seat4.setSeatNumber(1);
            seat4.setSeatRow(1);
            seat4.setTheatre(theatre);
            seat4 = seatRepository.save(seat4);


            Ticket ticket = new Ticket();
            ticket.setReservation(r1);
            ticket.setSeat(seat1);
            ticket.setShowTimes(st1);
            ticketRepository.save(ticket);

            Ticket ticket2 = new Ticket();
            ticket2.setReservation(r2);
            ticket2.setSeat(seat2);
            ticket2.setShowTimes(st2);
            ticketRepository.save(ticket2);

            Ticket ticket3 = new Ticket();
            ticket3.setReservation(r2);
            ticket3.setSeat(seat3);
            ticket3.setShowTimes(st3);
            ticketRepository.save(ticket3);

            Ticket ticket4 = new Ticket();
            ticket4.setReservation(r3);
            ticket4.setSeat(seat4);
            ticket4.setShowTimes(st3);
            ticketRepository.save(ticket4);
        };
    }
}