package org.example.kinograf.mapper;

import org.example.kinograf.DTO.ShowTimesDTO;
import org.example.kinograf.model.ShowTimes;

public class ShowTimesMapper {

    public static ShowTimesDTO toDTO(ShowTimes showTimes) {
        return new ShowTimesDTO(
                showTimes.getShowTimesId(),
                showTimes.getMovie().getMovieId(),
                showTimes.getTheatre().getTheatreId(),
                showTimes.getTimeOfDay()
        );
    }
}
