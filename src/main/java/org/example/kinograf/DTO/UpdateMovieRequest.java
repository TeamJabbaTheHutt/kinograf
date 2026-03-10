package org.example.kinograf.DTO;

public record UpdateMovieRequest(
        String name,
        String omdbID
) {
}
