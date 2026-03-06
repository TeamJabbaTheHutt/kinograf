package org.example.kinograf.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Theatre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long theatreId;

    String theatreName;
    int capacity;

    @OneToMany(mappedBy = "theatre")
    private List<Seat> seats;

    @OneToMany(mappedBy = "theatre")
    private List<ShowTimes> showtimes;

    public Theatre() {}

    public Theatre(String theatreName, int capacity) {
        this.theatreName = theatreName;
        this.capacity = capacity;
    }

    public Long getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(Long theatreId) {
        this.theatreId = theatreId;
    }

    public String getTheatreName() {
        return theatreName;
    }

    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
