package org.example.kinograf.service;


import org.example.kinograf.DTO.TicketDTO;
import org.example.kinograf.mapper.TicketMapper;
import org.example.kinograf.model.Reservation;
import org.example.kinograf.model.Seat;
import org.example.kinograf.model.ShowTimes;
import org.example.kinograf.model.Ticket;
import org.example.kinograf.repository.ReservationRepository;
import org.example.kinograf.repository.SeatRepository;
import org.example.kinograf.repository.ShowtimeRepository;
import org.example.kinograf.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final ShowtimeRepository showtimesRepository;
    private final ReservationRepository reservationRepository;
    private final SeatRepository seatRepository;
    //crud

    public TicketServiceImpl(TicketRepository ticketRepository, ShowtimeRepository showtimeRepository, ReservationRepository reservationRepository, SeatRepository seatRepository) {
        this.ticketRepository = ticketRepository;
        this.showtimesRepository = showtimeRepository;
        this.reservationRepository = reservationRepository;
        this.seatRepository = seatRepository;
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
    public TicketDTO createTicket(Long showingId, Long reservationId, Long seatId, double price) {

        ShowTimes showTimes = showtimesRepository.findById(showingId)
                .orElseThrow();

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow();

        Seat seat = seatRepository.findById(seatId)
                .orElseThrow();

        Ticket ticket = new Ticket();
        ticket.setPrice(price);
        ticket.setShowTimes(showTimes);
        ticket.setReservation(reservation);
        ticket.setSeat(seat);

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

        Ticket existing = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        ShowTimes showTimes = showtimesRepository.findById(showingId)
                .orElseThrow(() -> new RuntimeException("Showtime not found"));

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        existing.setShowTimes(showTimes);
        existing.setReservation(reservation);
        existing.setSeat(seat);
        existing.setPrice(price);

        Ticket saved = ticketRepository.save(existing);

        return TicketMapper.toDTO(saved);
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
