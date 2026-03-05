package org.example.kinograf.config;

import org.example.kinograf.model.Movie;
import org.example.kinograf.model.Reservation;
import org.example.kinograf.repository.MovieRepository;
import org.example.kinograf.repository.ReservationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitApp {

    @Bean
    CommandLineRunner initDatabase(MovieRepository movieRepository,
                                   ReservationRepository reservationRepository) {

        return args -> {

            // Movies
            if (movieRepository.count() == 0) {

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

                movieRepository.save(movie1);
                movieRepository.save(movie2);
                movieRepository.save(movie3);

                System.out.println("Movies added to H2 database");
            }


            // Reservations
            if (reservationRepository.count() == 0) {

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

                System.out.println("Reservations added to H2 database");
            }
        };
    }
}

