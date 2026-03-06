package org.example.kinograf.service;

import org.example.kinograf.DTO.SeatDTO;
import org.example.kinograf.mapper.SeatMapper;
import org.example.kinograf.model.Seat;
import org.example.kinograf.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class SeatService {
    private final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }


    public List<SeatDTO> getAllSeats() {
        return seatRepository.findAll().stream().map(SeatMapper::toDTO).toList();
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
        Seat seat = new Seat();

        seat.setTheatreId(theatreId);
        seat.setSeatRow(seatRow);
        seat.setSeatNumber(seatNumber);

        return SeatMapper.toDTO(seatRepository.save(seat));
    }

    public SeatDTO updateSeat(
            Long seatId,
            Long theatreId,
            int seatRow,
            int seatNumber
    ) {
        Seat updated = seatRepository.findById(seatId).orElse(null);

        if (updated == null) {
            return null;
        }

        updated.setSeatRow(seatRow);
        updated.setSeatNumber(seatNumber);
        updated.setTheatreId(theatreId);
        updated.setSeatId(seatId);

        return SeatMapper.toDTO(seatRepository.save(updated));
    }

    public void deleteSeat(Long seatId) {
        seatRepository.deleteById(seatId);
    }
}
