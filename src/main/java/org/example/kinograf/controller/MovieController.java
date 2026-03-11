package org.example.kinograf.controller;

import org.example.kinograf.DTO.createRequest.CreateMovieRequest;
import org.example.kinograf.DTO.MovieDTO;
import org.example.kinograf.DTO.updateRequest.UpdateMovieRequest;
import org.example.kinograf.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieDTO> getMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMovieById(@PathVariable Long id) {
        Optional<MovieDTO> movie = movieService.getMovieById(id);
        if (movie.isPresent()) {
            return ResponseEntity.ok(movie.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<MovieDTO> createMovie(@RequestBody CreateMovieRequest request) {
        MovieDTO created = movieService.createMovie(
                request.name(),
                request.omdbID()
        );
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> updateMovie(@PathVariable Long id, @RequestBody UpdateMovieRequest request) {
        MovieDTO updated = movieService.updateMovie(
                id,
                request.name(),
                request.omdbID()
        );
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
    }
}

