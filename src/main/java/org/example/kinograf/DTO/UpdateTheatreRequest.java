package org.example.kinograf.DTO;

public record UpdateTheatreRequest(
        String theatreName,
        int capacity,
        int rowsInTheatre,
        int seatsInTheatre
){
}
