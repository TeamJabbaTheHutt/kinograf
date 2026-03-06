package org.example.kinograf.DTO;

public record CreateTicketRequest(
        Long showingId,
        Long reservationId,
        Long seatId,
        double price
) {
}
