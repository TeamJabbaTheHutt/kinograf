package org.example.kinograf.DTO;

public record UpdateTicketRequest(
        Long showingId,
        Long reservationId,
        Long seatId,
        double price
) {}
