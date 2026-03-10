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

    private int rowsInTheatre;
    private int seatsInTheatre;

    public Theatre() {}

    public Theatre(String theatreName, int capacity, int rowsInTheatre, int seatsInTheatre) {
        this.theatreName = theatreName;
        this.capacity = capacity;
        this.rowsInTheatre = rowsInTheatre;
        this.seatsInTheatre = seatsInTheatre;
    }

    public int getRowsInTheatre() {
        return rowsInTheatre;
    }

    public void setRowsInTheatre(int rowsInTheatre) {
        this.rowsInTheatre = rowsInTheatre;
    }

    public int getSeatsInTheatre() {
        return seatsInTheatre;
    }

    public void setSeatsInTheatre(int seatsInTheatre) {
        this.seatsInTheatre = seatsInTheatre;
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
