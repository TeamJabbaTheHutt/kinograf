package org.example.kinograf.controller;

import org.example.kinograf.DTO.CreateReservationRequest;
import org.example.kinograf.DTO.ReservationDTO;
import org.example.kinograf.DTO.UpdateReservationRequest;
import org.example.kinograf.model.Reservation;
import org.example.kinograf.repository.ReservationRepository;
import org.example.kinograf.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
public class ReservationController {
    private final ReservationService reservationService;


    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/reservations")
    public List<ReservationDTO> getReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/reservations/{id}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable long id) {
        Optional<ReservationDTO> reservation = reservationService.getReservationById(id);
        if (reservation.isPresent()) {
            return ResponseEntity.ok(reservation.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/reservation")
    public ResponseEntity<ReservationDTO> addReservation(@RequestBody CreateReservationRequest request) {
        ReservationDTO saved = reservationService.createReservation(
                request.customerName(),
                request.phoneNumber()
        );
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/reservations/{id}")
    public ResponseEntity<ReservationDTO> updateReservation(@PathVariable long id, @RequestBody UpdateReservationRequest request) {
        ReservationDTO update = reservationService.updateReservation(
                id,
                request.customerName(),
                request.phoneNumber()
        );
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }

}
