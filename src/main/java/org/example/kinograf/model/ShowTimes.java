package org.example.kinograf.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class ShowTimes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long showTimesId;

    private LocalDateTime timeOfDay;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "theatre_id")
    private Theatre theatre;

    @OneToMany(mappedBy = "showTimes")
    private List<Ticket> tickets;

    public ShowTimes() {}

    public ShowTimes(Movie movie, Theatre theatre, LocalDateTime timeOfDay) {
        this.movie = movie;
        this.theatre = theatre;
        this.timeOfDay = timeOfDay;
    }

    public Long getShowTimesId() {
        return showTimesId;
    }

    public void setShowTimesId(Long showTimesId) {
        this.showTimesId = showTimesId;
    }

    public LocalDateTime getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(LocalDateTime timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
