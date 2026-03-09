package org.example.kinograf.DTO;

public record CreateTheatreRequest(
        String theatreName,
        int capacity,
        int rowsInTheatre,
        int seatsInTheatre
) {
}
