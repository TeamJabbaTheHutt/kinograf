package org.example.kinograf.model;

import jakarta.persistence.*;

import java.util.List;


@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;

    private String name;
    private String omdbID;

    @OneToMany(mappedBy = "movie")
    private List<ShowTimes> showTimes;


    public Movie() {
    }

    public Movie(Long movieId, String name, String omdbID) {
        this.movieId = movieId;
        this.name = name;
        this.omdbID = omdbID;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOmdbID() {
        return omdbID;
    }

    public void setOmdbID(String omdbID) {
        this.omdbID = omdbID;
    }

    public List<ShowTimes> getShowTimes() {
        return showTimes;
    }

    public void setShowTimes(List<ShowTimes> showTimes) {
        this.showTimes = showTimes;
    }
}
