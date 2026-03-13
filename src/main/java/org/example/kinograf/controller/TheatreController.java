package org.example.kinograf.controller;

import org.example.kinograf.DTO.TheatreDTO;
import org.example.kinograf.DTO.createRequest.CreateTheatreRequest;
import org.example.kinograf.service.TheatreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theatre")
public class TheatreController {

    private final TheatreService theatreService;

    public TheatreController(TheatreService theatreService) {
        this.theatreService = theatreService;
    }

    @PostMapping()
    public ResponseEntity<TheatreDTO> createTheatre(@RequestBody CreateTheatreRequest request) {
        TheatreDTO saved = theatreService.createTheatre(
                request.theatreName(),
                request.capacity(),
                request.rowsInTheatre(),
                request.seatsInTheatre()
        );
        return ResponseEntity.ok(saved);
    }
}
