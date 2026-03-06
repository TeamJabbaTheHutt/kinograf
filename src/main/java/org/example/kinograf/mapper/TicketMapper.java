package org.example.kinograf.mapper;

import org.example.kinograf.DTO.TicketDTO;
import org.example.kinograf.model.Ticket;

public class TicketMapper {

    public static TicketDTO toDTO(Ticket ticket) {
        return new TicketDTO(
                ticket.getTicketId(),
                ticket.getShowingId(),
                ticket.getReservationId(),
                ticket.getSeatId(),
                ticket.getPrice()
        );
    }

    public static Ticket fromDTO(TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();
        ticket.setTicketId(ticketDTO.ticketId());
        ticket.setShowingId(ticketDTO.showingId());
        ticket.setReservationId(ticketDTO.reservationId());
        ticket.setSeatId(ticketDTO.seatId());
        ticket.setPrice(ticketDTO.price());

        return ticket;
    }
}
