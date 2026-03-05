package org.example.kinograf.reservation;

import org.example.kinograf.model.Reservation;
import org.example.kinograf.repository.ReservationRepository;
import org.example.kinograf.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class ReservationServiceTest {
    private ReservationService reservationService;
    private ReservationRepository reservationRepository;
    @BeforeEach
    void setup() {
        reservationService = new ReservationService();
        reservationRepository = new ReservationRepository();
    }

    @Test
    void shouldCreateReservationWhenValidInput() {

        Reservation reservation = reservationService.createReservation(
                "John doe",
                "21212121"
        );

        when(reservationRepository.save(any().thenReturn(reservation)));
        assertNotNull(reservation);
        assertEquals("John doe", reservation.getCustomername());
        assertEquals("21212121", reservation.getPhoneNumber());

        verify(reservationRepository.save(any));
    }

    @Test
    void shouldUpdateReservation() {
        Reservation reservation = new Reservation(1L, "Jonny", "21212121");

        when(reservationRepository.findById(1L)).thenReturn(Optional.of(any()));
        when(reservationRepository.save(any())).thenReturn(reservation);

        Reservation updated = reservationService.updateReservation(1L, "erik" , "32323232");
        assertEquals("erik", reservation.getCustomerName());
        assertEquals("32323232", reservation.getCustomerName());

        verify(reservationRepository).save(reservation);
    }

    @Test
    void shouldGetAllReservations() {
        List<Reservation> reservations = List.of(
                new Reservation(1L, "jon", "11111111"),
                new Reservation(2L, "Jane", "222222222"),
        );
        when(reservationRepository.findAll()).thenReturn(reservations);
        List<Reservations> result = reservationService.getAllReservations();
        assertEquals(2, result.size());
        verify(reservationRepository).findAll();

    }

    @Test
    void shouldDeleteReservation() {
        doNothing().when(reservationRepository).deleteById(1L);
        reservationService.deleteReservation(1L);
        verify(reservationRepository).deletebyId(1L);
    }

}
