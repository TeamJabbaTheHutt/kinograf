package org.example.kinograf.DTO.updateRequest;

public record UpdateTicketRequest(
        Long showingId,
        Long reservationId,
        Long seatId,
        double price
) {}
