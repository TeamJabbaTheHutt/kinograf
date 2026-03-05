package org.example.kinograf.config;

import org.example.kinograf.model.Reservation;
import org.example.kinograf.repository.ReservationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitApp {

    @Bean
    CommandLineRunner initReservations(ReservationRepository reservationRepository) {
        return args -> {

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

                System.out.println("Test reservations created");
            }
        };
    }
}