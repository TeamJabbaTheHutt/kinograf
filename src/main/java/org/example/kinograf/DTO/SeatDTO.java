package org.example.kinograf.DTO;

public record SeatDTO(
        Long seatId,
        Long theatreId,
        int seatRow,
        int seatNumber
) {
}
