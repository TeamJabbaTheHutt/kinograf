package org.example.kinograf.DTO;

public record TheatreDTO(
        Long theatreId,
        String theatreName,
        int capacity,
        int rowsInTheatre,
        int seatsInTheatre
) {
}
