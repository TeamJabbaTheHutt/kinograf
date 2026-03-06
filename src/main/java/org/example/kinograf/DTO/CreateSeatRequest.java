package org.example.kinograf.DTO;

public record CreateSeatRequest(
        int seatNumber,
        int rowNumber,
        Long theatreId
) {
}
