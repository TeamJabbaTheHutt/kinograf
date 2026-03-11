package org.example.kinograf.DTO.createRequest;

public record CreateTheatreRequest(
        String theatreName,
        int capacity,
        int rowsInTheatre,
        int seatsInTheatre
) {
}
