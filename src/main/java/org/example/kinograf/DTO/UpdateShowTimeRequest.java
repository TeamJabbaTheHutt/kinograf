package org.example.kinograf.DTO;

import java.time.LocalDate;

public record UpdateShowTimeRequest(
        Long movieId,
        Long theatreId,
        LocalDate timeOfDay
) {
}
