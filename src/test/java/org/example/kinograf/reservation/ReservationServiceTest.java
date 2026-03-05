package org.example.kinograf.reservation;

import org.example.kinograf.DTO.ReservationDTO;
import org.example.kinograf.DTO.UpdateReservationRequest;
import org.example.kinograf.model.Reservation;
import org.example.kinograf.repository.ReservationRepository;
import org.example.kinograf.service.ReservationMapper;
import org.example.kinograf.service.ReservationService;
import org.example.kinograf.service.ReservationServiceImpl;
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
        reservationRepository = mock(ReservationRepository.class);
        reservationService = new ReservationServiceImpl(reservationRepository);
    }

    @Test
    void shouldCreateReservationWhenValidInput() {

        ReservationDTO reservationDTO = reservationService.createReservation(
                "John doe",
                "21212121"
        );
        Reservation reservation = ReservationMapper.fromDto(reservationDTO);
        when(reservationRepository.save(any())).thenReturn(reservation);
        assertNotNull(reservation);
        assertEquals("John doe", reservation.getCustomerName());
        assertEquals("21212121", reservation.getPhoneNumber());

        verify(reservationRepository.save(any()));
    }

    @Test
    void shouldUpdateReservation() {
        UpdateReservationRequest request = new UpdateReservationRequest("jonny", "21212121");
        Long id = 1L;
        ReservationDTO update = reservationService.updateReservation(
                id,
                request.customerName(),
                request.phoneNumber()
        );


        when(reservationRepository.findById(1L)).thenReturn(Optional.of(any()));
        when(reservationRepository.save(any())).thenReturn(update);

        ReservationDTO updated = reservationService.updateReservation(1L, "erik" , "32323232");
        Reservation reservation = ReservationMapper.fromDto(updated);
        assertEquals("erik", reservation.getCustomerName());
        assertEquals("32323232", reservation.getCustomerName());

        verify(reservationRepository).save(reservation);
    }

    @Test
    void shouldGetAllReservations() {

        Reservation r1 = new Reservation();
        r1.setCustomerName("jon");
        r1.setPhoneNumber("11111111");
        Reservation r2 = new Reservation();
        r2.setCustomerName("Jane");
        r2.setPhoneNumber("222222222");
        List<Reservation> reservations = List.of(
                r1,
                r2
        );
        when(reservationRepository.findAll()).thenReturn(reservations);
        List<ReservationDTO> result = reservationService.getAllReservations();
        assertEquals(2, result.size());
        verify(reservationRepository).findAll();

    }

    @Test
    void shouldDeleteReservation() {
        doNothing().when(reservationRepository).deleteById(1L);
        reservationService.deleteReservation(1L);
        verify(reservationRepository).deleteById(1L);
    }

}
