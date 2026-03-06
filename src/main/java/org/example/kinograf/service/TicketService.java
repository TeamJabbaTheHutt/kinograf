package org.example.kinograf.service;

import org.example.kinograf.DTO.TicketDTO;

import java.util.List;
import java.util.Optional;

public interface TicketService {
    List<TicketDTO> getAllTickets();
    Optional<TicketDTO> getTicketById(Long id);

    // crud
    TicketDTO createTicket(
            Long showingId,
            Long reservationId,
            Long seatId,
            double price
    );

    TicketDTO updateTicket(
            Long ticketId,
            Long showingId,
            Long reservationId,
            Long seatId,
            double price
    );

    void deleteTicket(Long ticketId);


}
