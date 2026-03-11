package org.example.kinograf.controller;

import org.example.kinograf.DTO.createRequest.CreateShowTimeRequest;
import org.example.kinograf.DTO.ShowTimesDTO;
import org.example.kinograf.DTO.updateRequest.UpdateShowTimeRequest;
import org.example.kinograf.service.ShowtimeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/showTimes")
public class ShowTimesController {

    private final ShowtimeService showtimeService;

    public ShowTimesController(ShowtimeService showtimeService) {
        this.showtimeService = showtimeService;
    }

    @GetMapping
    public List<ShowTimesDTO> getShowTimes() {
        return showtimeService.getALlShowTimes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowTimesDTO> getShowTimesById(@PathVariable Long id) {
        Optional<ShowTimesDTO> showTime = showtimeService.getShowTimesById(id);
        if (showTime.isPresent()) {
            return ResponseEntity.ok(showTime.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ShowTimesDTO> createShowTime(@RequestBody CreateShowTimeRequest request) {
        ShowTimesDTO created = showtimeService.createShowTimes(
                request.movieId(),
                request.theatreId(),
                request.timeOfDay()
        );
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShowTimesDTO> updateShowTime(@PathVariable Long id, @RequestBody UpdateShowTimeRequest request) {
        ShowTimesDTO updated = showtimeService.updateShowTime(
                id,
                request.movieId(),
                request.theatreId(),
                request.timeOfDay()
        );
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public void deleteShowTime(@PathVariable Long id) {
        showtimeService.deleteShowTimes(id);
    }
}
