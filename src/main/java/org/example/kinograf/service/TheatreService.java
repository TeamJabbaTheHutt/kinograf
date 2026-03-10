package org.example.kinograf.service;

import org.example.kinograf.DTO.TheatreDTO;
import org.example.kinograf.model.Theatre;

import java.util.List;
import java.util.Optional;

public interface TheatreService {

    List<TheatreDTO> getAllTheatres();
    List<Theatre> getAllTheatreEntities();

    Optional<TheatreDTO> getTheatreById(Long theatreId);
    Optional<Theatre> getTheatreEntityById(Long theatreId);

    TheatreDTO createTheatre(String theatreName, int capacity, int rowsInTheatre, int seatsInTheatre);
    TheatreDTO updateTheatre(Long id, String theatreName, int capacity, int rowsInTheatre, int seatsInTheatre);
    void deleteTheatre(Long theatreId);
}
