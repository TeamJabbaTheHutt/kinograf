package org.example.kinograf.DTO;

public record UpdateMovieRequest(
        String name,
        String categories,
        int ageLimit
) {
}
