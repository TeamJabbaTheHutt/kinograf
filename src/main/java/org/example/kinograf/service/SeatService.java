package org.example.kinograf.service;

import org.example.kinograf.DTO.ReservationDTO;
import org.example.kinograf.DTO.SeatDTO;
import org.example.kinograf.mapper.SeatMapper;
import org.example.kinograf.model.Reservation;
import org.example.kinograf.model.Seat;
import org.example.kinograf.model.Theatre;
import org.example.kinograf.repository.SeatRepository;
import org.example.kinograf.repository.TheatreRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SeatService {

    private final SeatRepository seatRepository;
    private final TheatreRepository theatreRepository;

    public SeatService(SeatRepository seatRepository, TheatreRepository theatreRepository) {
        this.seatRepository = seatRepository;
        this.theatreRepository = theatreRepository;
    }

    public List<SeatDTO> getAllSeats() {
        List<Seat> seats = seatRepository.findAll();
        List<SeatDTO> seatDTOS = new ArrayList<>();

        for (Seat seat : seats) {
            seatDTOS.add(SeatMapper.toDTO(seat));
        }
        return seatDTOS;

    }

    public Optional<SeatDTO> getSeatBySeatId(Long seatId) {

        Optional<Seat> seat = seatRepository.findById(seatId);
        if (seat.isPresent()) {
            return Optional.of(SeatMapper.toDTO(seat.get()));
        }
        return Optional.empty();
    }

    public SeatDTO createSeat(
            Long theatreId,
            int seatRow,
            int seatNumber
    ) {

        Theatre theatre = theatreRepository.findById(theatreId)
                .orElseThrow(() -> new RuntimeException("Theatre not found"));

        Seat seat = new Seat();
        seat.setSeatRow(seatRow);
        seat.setSeatNumber(seatNumber);
        seat.setTheatre(theatre);

        return SeatMapper.toDTO(seatRepository.save(seat));
    }

    public SeatDTO updateSeat(
            Long seatId,
            Long theatreId,
            int seatRow,
            int seatNumber
    ) {

        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        Theatre theatre = theatreRepository.findById(theatreId)
                .orElseThrow(() -> new RuntimeException("Theatre not found"));

        seat.setSeatRow(seatRow);
        seat.setSeatNumber(seatNumber);
        seat.setTheatre(theatre);

        return SeatMapper.toDTO(seatRepository.save(seat));
    }

    public void deleteSeat(Long seatId) {
        seatRepository.deleteById(seatId);
    }
}