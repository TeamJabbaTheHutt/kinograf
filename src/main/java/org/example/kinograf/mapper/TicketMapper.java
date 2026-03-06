package org.example.kinograf.mapper;

import org.example.kinograf.DTO.TicketDTO;
import org.example.kinograf.model.Ticket;

public class TicketMapper {

    public static TicketDTO toDTO(Ticket ticket) {
        return new TicketDTO(
                ticket.getTicketId(),
                ticket.getShowTimes().getShowTimesId(),
                ticket.getReservation().getReservationId(),
                ticket.getSeat().getSeatId(),
                ticket.getPrice()
        );
    }

    public static Ticket fromDTO(TicketDTO dto) {
        Ticket ticket = new Ticket();
        ticket.setTicketId(dto.ticketId());
        ticket.setPrice(dto.price());
        return ticket;
    }
}
