package org.example.kinograf.DTO.updateRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UpdateShowTimeRequest(
        Long movieId,
        Long theatreId,
        LocalDateTime timeOfDay
) {
}
