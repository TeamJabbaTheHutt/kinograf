package org.example.kinograf.service;

import org.example.kinograf.DTO.ReservationDTO;
import org.example.kinograf.model.Reservation;
import org.example.kinograf.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }


    @Override
    public List<ReservationDTO> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        List<ReservationDTO> dtos = new ArrayList<>();
        for (Reservation reservation : reservations) {
            dtos.add(ReservationMapper.toDto(reservation));
        }
        return dtos;
    }

    @Override
    public Optional<ReservationDTO> getReservationById(long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if (reservation.isPresent()) {
            return Optional.of(ReservationMapper.toDto(reservation.get()));
        }
        return Optional.empty();
    }

    @Override
    public void deleteReservation(long reservationId)  {
        if (!reservationRepository.existsById(reservationId)) {
            throw new RuntimeException("Reservation not found");
        }
        reservationRepository.deleteById(reservationId);
    }

    @Override
    public ReservationDTO createReservation(String customerName, String phoneNumber) {
        Reservation reservation = new Reservation();
        reservation.setCustomerName(customerName);
        reservation.setPhoneNumber(phoneNumber);

        Reservation saved = reservationRepository.save(reservation);
        return ReservationMapper.toDto(saved);
    }

    @Override
    public ReservationDTO updateReservation(
            long reservationId,
            String customerName,
            String phoneNumber
    ) {
        Reservation existing = reservationRepository.findById(reservationId).orElse(null);
        return ReservationMapper.toDto(reservationRepository.save(existing));
    }

}
