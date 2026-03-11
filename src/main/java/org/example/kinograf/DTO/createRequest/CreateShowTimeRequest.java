package org.example.kinograf.DTO.createRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CreateShowTimeRequest(
        Long movieId,
        Long theatreId,
        LocalDateTime timeOfDay) {
}
