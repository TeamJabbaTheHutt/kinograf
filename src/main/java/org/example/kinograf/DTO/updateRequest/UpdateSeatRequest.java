package org.example.kinograf.DTO.updateRequest;

public record UpdateSeatRequest(
        int seatNumber,
        int rowNumber,
        Long theatreId
) {
}
