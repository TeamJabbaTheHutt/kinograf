package org.example.kinograf.model;

//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;
    private double price;


    @ManyToOne
    @JoinColumn(name = "showtimes_id")
    private ShowTimes showTimes;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    public Ticket() {}

    public Ticket(Long ticketId, double price, ShowTimes showTimes, Reservation reservation, Seat seat) {
        this.ticketId = ticketId;
        this.price = price;
        this.showTimes = showTimes;
        this.reservation = reservation;
        this.seat = seat;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ShowTimes getShowTimes() {
        return showTimes;
    }

    public void setShowTimes(ShowTimes showTimes) {
        this.showTimes = showTimes;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
    //    public Ticket(Long ticketId, Long showingId, Long reservationId, Long seatId, double price) {
//        this.ticketId = ticketId;
//        this.showingId = showingId;
//        this.reservationId = reservationId;
//        this.seatId = seatId;
//        this.price = price;
//    }
//
//    public Long getTicketId() {
//        return ticketId;
//    }
//
//    public void setTicketId(Long ticketId) {
//        this.ticketId = ticketId;
//    }
//
//    public Long getShowingId() {
//        return showingId;
//    }
//
//    public void setShowingId(Long showingId) {
//        this.showingId = showingId;
//    }
//
//    public Long getReservationId() {
//        return reservationId;
//    }
//
//    public void setReservationId(Long reservationId) {
//        this.reservationId = reservationId;
//    }
//
//    public Long getSeatId() {
//        return seatId;
//    }
//
//    public void setSeatId(Long seatId) {
//        this.seatId = seatId;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }
}
