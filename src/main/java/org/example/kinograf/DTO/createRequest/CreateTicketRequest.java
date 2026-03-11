package org.example.kinograf.DTO.createRequest;

public record CreateTicketRequest(
        Long showingId,
        Long reservationId,
        Long seatId,
        double price
) {
}
