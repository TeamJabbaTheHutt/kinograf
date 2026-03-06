package org.example.kinograf.DTO;

import java.time.LocalDate;

public record ShowTimesDTO(
        Long showTimesId,
        Long movieId,
        Long theatreId,
        LocalDate timeOfDay) {
}
