package org.example.kinograf.controller;


import org.example.kinograf.DTO.createRequest.CreateTicketRequest;
import org.example.kinograf.DTO.TicketDTO;
import org.example.kinograf.DTO.updateRequest.UpdateTicketRequest;
import org.example.kinograf.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    private final TicketService ticketService;
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }
    // crud


    @GetMapping("/tickets")
    public List<TicketDTO> getTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/tickets/{id}")
    public ResponseEntity<TicketDTO> getTicket(@PathVariable Long id) {
        Optional<TicketDTO> ticket = ticketService.getTicketById(id);
        if (ticket.isPresent()) {
            return ResponseEntity.ok(ticket.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/ticket")
    public ResponseEntity<TicketDTO> createTicket(@RequestBody CreateTicketRequest request) {
        TicketDTO saved = ticketService.createTicket(
                request.showingId(),
                request.reservationId(),
                request.seatId(),
                request.price()
        );
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/tickets/{id}")
    public ResponseEntity<TicketDTO> updateTicket(@PathVariable Long id, @RequestBody UpdateTicketRequest request) {
        TicketDTO saved = ticketService.updateTicket(
                id,
                request.showingId(),
                request.reservationId(),
                request.seatId(),
                request.price()
        );
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/tickets/{id}")
    public ResponseEntity<TicketDTO> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }

}
