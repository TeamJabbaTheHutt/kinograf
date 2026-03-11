package org.example.kinograf.service;

import org.example.kinograf.DTO.ShowTimesDTO;
import org.example.kinograf.mapper.ShowTimesMapper;
import org.example.kinograf.model.Movie;
import org.example.kinograf.model.ShowTimes;
import org.example.kinograf.model.Theatre;
import org.example.kinograf.repository.MovieRepository;
import org.example.kinograf.repository.ShowtimeRepository;
import org.example.kinograf.repository.TheatreRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShowtimeService {

    private final ShowtimeRepository showtimeRepository;
    private final MovieRepository movieRepository;
    private final TheatreRepository theatreRepository;

    public ShowtimeService(ShowtimeRepository showtimeRepository, MovieRepository movieRepository, TheatreRepository theatreRepository) {
        this.showtimeRepository = showtimeRepository;
        this.movieRepository = movieRepository;
        this.theatreRepository = theatreRepository;
    }

    public List<ShowTimesDTO> getALlShowTimes() {
        List<ShowTimes> showTimes = showtimeRepository.findAll();
        List<ShowTimesDTO> dtos = new ArrayList<>();

        for (ShowTimes showtimes : showTimes) {
            dtos.add(ShowTimesMapper.toDTO(showtimes));
        }
        return dtos;

    }

    public Optional<ShowTimesDTO> getShowTimesById(Long id) {
        Optional<ShowTimes> showTimes = showtimeRepository.findById(id);
        if (showTimes.isPresent()) {
            return Optional.of(ShowTimesMapper.toDTO(showTimes.get()));
        }
        return Optional.empty();
    }

    public ShowTimesDTO createShowTimes(Long movieId, Long theatreId, LocalDateTime timeOfDay) {

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        Theatre theatre = theatreRepository.findById(theatreId)
                .orElseThrow(() -> new RuntimeException("Theatre not found"));

        timeOfDay = timeOfDay.withSecond(0).withNano(0);

        ShowTimes showTimes = new ShowTimes();
        showTimes.setMovie(movie);
        showTimes.setTheatre(theatre);
        showTimes.setTimeOfDay(timeOfDay);

        showtimeRepository.save(showTimes);

        return ShowTimesMapper.toDTO(showTimes);
    }

    public ShowTimesDTO updateShowTime(
            Long showTimesId,
            Long movieId,
            Long theatreId,
            LocalDateTime timeOfDay
    ) {

        ShowTimes updated = showtimeRepository.findById(showTimesId).orElse(null);

        if (updated == null) {
            return null;
        }

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        Theatre theatre = theatreRepository.findById(theatreId)
                .orElseThrow(() -> new RuntimeException("Theatre not found"));

        timeOfDay = timeOfDay.withSecond(0).withNano(0);

        updated.setMovie(movie);
        updated.setTheatre(theatre);
        updated.setTimeOfDay(timeOfDay);

        showtimeRepository.save(updated);

        return ShowTimesMapper.toDTO(updated);
    }

    public void deleteShowTimes(Long id) {
        showtimeRepository.deleteById(id);
    }
}
