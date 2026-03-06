package org.example.kinograf.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class ShowTimes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long showTimesId;

    private Long movieId;
    private Long theatreId;
    private LocalDate timeOfDay;

    public ShowTimes() {
    }

    public ShowTimes(Long showTimesId, Long movieId, Long theatreId, LocalDate timeOfDay) {
        this.showTimesId = showTimesId;
        this.movieId = movieId;
        this.theatreId = theatreId;
        this.timeOfDay = timeOfDay;
    }

    public Long getShowTimesId() {
        return showTimesId;
    }

    public void setShowTimesId(Long showTimesId) {
        this.showTimesId = showTimesId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(Long theatreId) {
        this.theatreId = theatreId;
    }

    public LocalDate getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(LocalDate timeOfDay) {
        this.timeOfDay = timeOfDay;
    }
}

