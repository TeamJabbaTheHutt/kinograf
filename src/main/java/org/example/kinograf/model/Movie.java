package org.example.kinograf.model;

import jakarta.persistence.*;

import java.util.List;


@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;

    private String omdbID;
    private String imageURL;
    private String name;
    private String description;
    private String duration;
    private String rating;
    private String categories;
    private int ageLimit;

    @OneToMany(mappedBy = "movie")
    private List<ShowTimes> showTimes;

    public Movie() {
    }

    public Movie(String name, String categories, int ageLimit) {
        this.name = name;
        this.categories = categories;
        this.ageLimit = ageLimit;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getOmdbID() {
        return omdbID;
    }

    public void setOmdbID(String omdbID) {
        this.omdbID = omdbID;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    public List<ShowTimes> getShowTimes() {
        return showTimes;
    }

    public void setShowTimes(List<ShowTimes> showTimes) {
        this.showTimes = showTimes;
    }
}
