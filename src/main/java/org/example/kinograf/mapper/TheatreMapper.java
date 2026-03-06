package org.example.kinograf.mapper;

import org.example.kinograf.DTO.TheatreDTO;
import org.example.kinograf.model.Theatre;

public class TheatreMapper {

    public static TheatreDTO toDTO(Theatre theatre) {
        return new TheatreDTO(
                theatre.getTheatreId(),
                theatre.getTheatreName(),
                theatre.getCapacity()
        );
    }

    public static Theatre fromDTO(TheatreDTO theatreDTO) {
        Theatre theatre = new Theatre();
        theatre.setTheatreId(theatreDTO.theatreId());
        theatre.setTheatreName(theatreDTO.theatreName());
        theatre.setCapacity(theatreDTO.capacity());
        return theatre;
    }
}
