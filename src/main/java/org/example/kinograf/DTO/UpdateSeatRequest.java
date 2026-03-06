package org.example.kinograf.DTO;

public record UpdateSeatRequest(
        int seatNumber,
        int rowNumber,
        Long theatreId
) {
}
