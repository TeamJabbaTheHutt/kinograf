package org.example.kinograf.mapper;

import org.example.kinograf.DTO.TicketDTO;
import org.example.kinograf.model.Ticket;

public class TicketMapper {

    public static TicketDTO toDTO(Ticket ticket) {
        return new TicketDTO(
                ticket.getTicketId(),
                ticket.getShowTimes(),
                ticket.getReservation(),
                ticket.getSeat(),
                ticket.getPrice()
        );
    }

    public static Ticket fromDTO(TicketDTO ticketDTO) {
        Ticket ticket = new Ticket();
        ticket.setTicketId(ticketDTO.ticketId());
        ticket.getShowTimes();
        ticket.getReservation();
        ticket.getSeat();
        ticket.setPrice(ticketDTO.price());

        return ticket;
    }
}
