package org.example.kinograf.DTO.updateRequest;

public record UpdateMovieRequest(
        String name,
        String omdbID
) {
}
