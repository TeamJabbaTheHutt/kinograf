package org.example.kinograf.DTO.createRequest;

public record CreateSeatRequest(
        int seatNumber,
        int rowNumber,
        Long theatreId
) {
}
