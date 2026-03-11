package org.example.kinograf.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ShowTimesDTO(
        Long showTimesId,
        Long movieId,
        Long theatreId,
        LocalDateTime timeOfDay) {
}
