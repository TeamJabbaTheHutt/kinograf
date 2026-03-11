package org.example.kinograf.reservation;

import org.example.kinograf.DTO.ReservationDTO;
import org.example.kinograf.model.Reservation;
import org.example.kinograf.repository.ReservationRepository;
import org.example.kinograf.mapper.ReservationMapper;
import org.example.kinograf.service.ReservationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservationServiceTest {

    private ReservationServiceImpl reservationService;
    private ReservationRepository reservationRepository;

    @BeforeEach
    void setup() {
        reservationRepository = mock(ReservationRepository.class);
        reservationService = new ReservationServiceImpl(reservationRepository);
    }

    @Test
    void shouldCreateReservationWhenValidInput() {

        Reservation reservation = new Reservation();
        reservation.setReservationId(1L);
        reservation.setCustomerName("John doe");
        reservation.setPhoneNumber("21212121");

        when(reservationRepository.save(any())).thenReturn(reservation);

        // Act
        ReservationDTO reservationDTO = reservationService.createReservation(
                "John doe",
                "21212121"
        );

        Reservation result = ReservationMapper.fromDto(reservationDTO);


        assertNotNull(reservationDTO);
        assertEquals("John doe", result.getCustomerName());
        assertEquals("21212121", result.getPhoneNumber());

        verify(reservationRepository).save(any());
    }

    @Test
    void shouldUpdateReservation() {

        Long id = 1L;
        Reservation existing = new Reservation();
        existing.setReservationId(id);
        existing.setCustomerName("jonny");
        existing.setPhoneNumber("21212121");

        when(reservationRepository.findById(id)).thenReturn(Optional.of(existing));
        when(reservationRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        ReservationDTO updated = reservationService.updateReservation(
                id,
                "erik",
                "32323232"
        );
        Reservation result = ReservationMapper.fromDto(updated);
        // Assert directly on DTO
        assertEquals("erik", result.getCustomerName());
        assertEquals("32323232", result.getPhoneNumber());

        verify(reservationRepository).save(existing);
    }

    @Test
    void shouldGetAllReservations() {

        Reservation r1 = new Reservation();
        r1.setReservationId(1L);
        r1.setCustomerName("jon");
        r1.setPhoneNumber("11111111");

        Reservation r2 = new Reservation();
        r2.setReservationId(2L);
        r2.setCustomerName("Jane");
        r2.setPhoneNumber("22222222");

        when(reservationRepository.findAll()).thenReturn(List.of(r1, r2));

        List<ReservationDTO> result = reservationService.getAllReservations();

        List<Reservation> resultset = new ArrayList<>();
        for (ReservationDTO dto : result) {
            resultset.add(ReservationMapper.fromDto(dto));
        }

        assertEquals(2, result.size());
        assertEquals("jon", resultset.get(0).getCustomerName());
        assertEquals("Jane", resultset.get(1).getCustomerName());

        verify(reservationRepository).findAll();
    }

    @Test
    void shouldDeleteReservation() {

        when(reservationRepository.existsById(1L)).thenReturn(true);
        doNothing().when(reservationRepository).deleteById(1L);

        reservationService.deleteReservation(1L);
        verify(reservationRepository).deleteById(1L);
    }
}