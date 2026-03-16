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
            Long seatId
    );

    TicketDTO updateTicket(
            Long ticketId,
            Long showingId,
            Long reservationId,
            Long seatId
    );

    void deleteTicket(Long ticketId);


}
