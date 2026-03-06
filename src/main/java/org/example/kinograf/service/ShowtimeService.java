package org.example.kinograf.service;

import org.example.kinograf.DTO.ShowTimesDTO;
import org.example.kinograf.mapper.ShowTimesMapper;
import org.example.kinograf.model.ShowTimes;
import org.example.kinograf.repository.ShowtimeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ShowtimeService {

    private final ShowtimeRepository showtimeRepository;

    public ShowtimeService(ShowtimeRepository showtimeRepository) {
        this.showtimeRepository = showtimeRepository;
    }

    public List<ShowTimesDTO> getALlShowTimes() {
        return showtimeRepository.findAll().stream().map(ShowTimesMapper::toDTO).toList();
    }

    public Optional<ShowTimesDTO> getShowTimesById(Long id) {
        Optional<ShowTimes> showTimes = showtimeRepository.findById(id);
        if (showTimes.isPresent()) {
            return Optional.of(ShowTimesMapper.toDTO(showTimes.get()));
        }
        return Optional.empty();
    }

    public ShowTimesDTO createShowTimes(
            Long movieId,
            Long theatreId,
            LocalDate timeOfDay
    ) {
        ShowTimes showTimes = new ShowTimes();

        showTimes.setMovieId(movieId);
        showTimes.setTheatreId(theatreId);
        showTimes.setTimeOfDay(timeOfDay);

        return ShowTimesMapper.toDTO(showtimeRepository.save(showTimes));
    }

    public ShowTimesDTO updateShowTime(
            Long showTimesId,
            Long movieId,
            Long theatreId,
            LocalDate timeOfDay
    ) {
        ShowTimes updated = showtimeRepository.findById(showTimesId).orElse(null);

        if (updated == null) {
            return null;
        }

        updated.setMovieId(movieId);
        updated.setTheatreId(theatreId);
        updated.setTimeOfDay(timeOfDay);

        return ShowTimesMapper.toDTO(showtimeRepository.save(updated));
    }

    public void deleteShowTimes(Long id) {
        showtimeRepository.deleteById(id);
    }
}
