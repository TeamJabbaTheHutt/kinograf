package org.example.kinograf.DTO;

public record CreateMovieRequest(
        String name,
        String categories,
        int ageLimit
) {
}