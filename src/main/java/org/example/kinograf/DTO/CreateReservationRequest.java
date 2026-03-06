package org.example.kinograf.DTO;

public record CreateReservationRequest(
        String customerName,
        String phoneNumber
) {

}
