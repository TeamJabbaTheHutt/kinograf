package org.example.kinograf.DTO.createRequest;

public record CreateMovieRequest(
        String name,
        String omdbID
) {
}