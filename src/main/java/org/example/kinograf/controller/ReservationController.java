package org.example.kinograf.controller;

import org.example.kinograf.DTO.createRequest.CreateReservationRequest;
import org.example.kinograf.DTO.ReservationDTO;
import org.example.kinograf.DTO.updateRequest.UpdateReservationRequest;
import org.example.kinograf.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService reservationService;


    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("")
    public List<ReservationDTO> getReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable long id) {
        Optional<ReservationDTO> reservation = reservationService.getReservationById(id);
        if (reservation.isPresent()) {
            return ResponseEntity.ok(reservation.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    public ResponseEntity<ReservationDTO> addReservation(@RequestBody CreateReservationRequest request) {
        ReservationDTO saved = reservationService.createReservation(
                request.customerName(),
                request.phoneNumber()
        );
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationDTO> updateReservation(@PathVariable long id, @RequestBody UpdateReservationRequest request) {
        ReservationDTO update = reservationService.updateReservation(
                id,
                request.customerName(),
                request.phoneNumber()
        );
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }

}
