package org.example.kinograf.service;

import org.example.kinograf.DTO.ReservationDTO;
import org.example.kinograf.model.Reservation;
import org.springframework.stereotype.Service;

@Service
public class ReservationMapper {

    public static ReservationDTO toDto(Reservation reservation) {
        return new ReservationDTO (
                reservation.getReservationId(),
                reservation.getCustomerName(),
                reservation.getPhoneNumber()
        );
    }

    public static Reservation fromDto(ReservationDTO dto) {
        Reservation reservation = new Reservation();
        reservation.setReservationId(dto.reservationId());
        reservation.setCustomerName(dto.customerName());
        reservation.setPhoneNumber(dto.phoneNumber());
        return reservation;
    }

}
