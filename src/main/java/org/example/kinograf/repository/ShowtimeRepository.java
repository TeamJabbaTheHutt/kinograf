package org.example.kinograf.repository;

import org.example.kinograf.model.Reservation;
import org.example.kinograf.model.ShowTimes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowtimeRepository extends JpaRepository<ShowTimes, Long> {
}
