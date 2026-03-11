package org.example.kinograf.DTO.updateRequest;

public record UpdateTheatreRequest(
        String theatreName,
        int capacity,
        int rowsInTheatre,
        int seatsInTheatre
){
}
