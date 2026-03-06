package org.example.kinograf.mapper;

import org.example.kinograf.DTO.SeatDTO;
import org.example.kinograf.model.Seat;

public class SeatMapper {

    public static SeatDTO toDTO(Seat seat) {
        return new SeatDTO(
                seat.getSeatId(),
                seat.getTheatreId(),
                seat.getSeatRow(),
                seat.getSeatNumber()
        );
    }


}
