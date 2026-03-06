package org.example.kinograf.service;


import org.example.kinograf.DTO.TicketDTO;
import org.example.kinograf.mapper.TicketMapper;
import org.example.kinograf.model.Ticket;
import org.example.kinograf.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;

    //crud
    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }
    //    List<TicketDTO> getAllTickets();

    @Override
    public List<TicketDTO> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        List<TicketDTO> dtos = new ArrayList<>();
        for (Ticket ticket : tickets) {
            dtos.add(TicketMapper.toDTO(ticket));
        }

        return dtos;
    }



//    Optional<TicketDTO> getTicketById();
//
    @Override
    public Optional<TicketDTO> getTicketById(Long id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isPresent()) {
            return Optional.of(TicketMapper.toDTO(ticket.get()));
        }
        return Optional.empty();
    }

//    // crud
//    TicketDTO createTicket(
//            Long showingId,
//            Long reservationId,
//            Long seatId,
//            double price
//    );
//
    @Override
    public TicketDTO createTicket(
            Long showingId,
            Long reservationId,
            Long seatId,
            double price
    ) {
        Ticket ticket = new Ticket();
        ticket.setSeatId(showingId);
        ticket.setReservationId(showingId);
        ticket.setSeatId(seatId);
        ticket.setPrice(price);

        Ticket saved = ticketRepository.save(ticket);
        return TicketMapper.toDTO(saved);

    }

//    TicketDTO updateTicket(
//            Long ticketId,
//            Long showingId,
//            Long reservationId,
//            Long seatId,
//            double price
//    );
//

    @Override
    public TicketDTO updateTicket(
            Long ticketId,
            Long showingId,
            Long reservationId,
            Long seatId,
            double price
    ) {
        Ticket existing = ticketRepository.findById(ticketId).orElse(null);
        existing.setShowingId(showingId);
        existing.setReservationId(reservationId);
        existing.setSeatId(seatId);
        existing.setPrice(price);

        return TicketMapper.toDTO(ticketRepository.save(existing));
    }
//    void deleteTicket(Long ticketId);

    @Override
    public void deleteTicket(Long id) {
        if (!ticketRepository.existsById(id)) {
            throw new RuntimeException("Ticket not found");
        }
        ticketRepository.deleteById(id);
    }



}
