package org.example.kinograf.DTO.updateRequest;

import java.time.LocalDate;

public record UpdateShowTimeRequest(
        Long movieId,
        Long theatreId,
        LocalDate timeOfDay
) {
}
