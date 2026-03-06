package org.example.kinograf.controller;

import org.example.kinograf.DTO.CreateTheatreRequest;
import org.example.kinograf.DTO.TheatreDTO;
import org.example.kinograf.DTO.UpdateTheatreRequest;
import org.example.kinograf.service.TheatreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/home")
public class HomeController {
    private final TheatreService theatreService;

    public HomeController(TheatreService theatreService) {
        this.theatreService = theatreService;
    }

    // For fetching info on movies to show on startpage
    @GetMapping()
    public String showHome() {
        return "static/index";
    }

    /// controller -> theatre, hvis vi mangler det

    @GetMapping("/theatres")
    public List<TheatreDTO> getTheatres() {
        return theatreService.getAllTheatres();
    }

    @GetMapping("/theatres/{id}")
    public ResponseEntity<TheatreDTO> getTheatre(@PathVariable Long id) {
        Optional<TheatreDTO> theatre = theatreService.getTheatreById(id);
        if (theatre.isPresent()) {
            return ResponseEntity.ok(theatre.get());
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping("/theatre")
    public ResponseEntity<TheatreDTO>  createTheatre(@RequestBody CreateTheatreRequest request) {
        TheatreDTO saved = theatreService.createTheatre(
                request.theatreName(),
                request.capacity()
        );
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/theatres/{id}")
    public ResponseEntity<TheatreDTO> updateTheatre(@PathVariable Long id, @RequestBody UpdateTheatreRequest request) {
        TheatreDTO saved = theatreService.updateTheatre(
                id,
                request.theatreName(),
                request.capacity()
        );
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/theatres/{id}")
    public ResponseEntity<TheatreDTO> deleteTheatre(@PathVariable Long id) {
        theatreService.deleteTheatre(id);
        return ResponseEntity.noContent().build();
    }

}
