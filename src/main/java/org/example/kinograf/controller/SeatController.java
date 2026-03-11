package org.example.kinograf.controller;


import org.example.kinograf.DTO.SeatDTO;
import org.example.kinograf.DTO.createRequest.CreateSeatRequest;
import org.example.kinograf.DTO.updateRequest.UpdateSeatRequest;
import org.example.kinograf.service.SeatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/seats")
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping
    public List<SeatDTO> getSeats() {
        return seatService.getAllSeats();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeatDTO> getSeat(@PathVariable Long id) {
        Optional<SeatDTO> seat = seatService.getSeatBySeatId(id);
        if (seat.isPresent()) {
            return ResponseEntity.ok(seat.get());
        }  else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<SeatDTO> createSeat(@RequestBody CreateSeatRequest request) {
        SeatDTO created = seatService.createSeat(
                request.theatreId(),
                request.rowNumber(),
                request.seatNumber()

        );
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeatDTO> updateSeat(@PathVariable Long id, @RequestBody UpdateSeatRequest request) {
        SeatDTO updated = seatService.updateSeat(
                id,
                request.theatreId(),
                request.rowNumber(),
                request.seatNumber()
        );
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public void deleteSeat(@PathVariable Long id) {
        seatService.deleteSeat(id);
    }

}
