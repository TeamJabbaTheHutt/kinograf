package org.example.kinograf.service;

import org.example.kinograf.DTO.ReservationDTO;
import org.example.kinograf.model.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    List<ReservationDTO> getAllReservations();
    Optional<ReservationDTO> getReservationById(long id);

    ReservationDTO createReservation(
            String customerName,
            String phoneNumber
    );

    ReservationDTO updateReservation(
            long reservationId,
            String customerName,
            String phoneNumber
    );

    void deleteReservation(
            long reservationId
    );

}
