package org.example.kinograf.DTO;

import java.time.LocalDate;

public record CreateShowTimeRequest(
        Long movieId,
        Long theatreId,
        LocalDate timeOfDay) {
}
