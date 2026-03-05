package org.example.kinograf.movie;

import org.example.kinograf.model.Movie;
import org.example.kinograf.repository.MovieRepository;
import org.example.kinograf.repository.ReservationRepository;
import org.example.kinograf.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class MovieServiceTest {

    private MovieService movieService;
    private MovieRepository movieRepository;

    @BeforeEach
    void setup() {
        movieRepository = mock(MovieRepository.class);
        movieService = new MovieService(movieRepository);
    }

    @Test
    void shouldCreateMovieWhenValidInput() {

        Movie movie = new Movie(1L, "movieName", "SCIFI", 10);

        when(movieRepository.save(any())).thenReturn(movie);

        Movie result = movieService.createMovie(
                "movieName",
                "SCIFI",
                10
        );

        assertNotNull(result);
        assertEquals("movieName", result.getMovieName());
        assertEquals("SCIFI", result.getCategories());
        assertEquals(10, result.getAgeLimit());

        verify(movieRepository).save(any());
    }

    @Test
    void shouldUpdateMovie() {

        Movie movie = new Movie(1L, "movieName", "SCIFI", 10);

        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));
        when(movieRepository.save(any())).thenReturn(movie);

        Movie updated = movieService.updateMovie(
                1L,
                "newMovie",
                "ACTION",
                15
        );

        assertEquals("newMovie", updated.getMovieName());
        assertEquals("ACTION", updated.getCategories());
        assertEquals(15, updated.getAgeLimit());

        verify(movieRepository).save(movie);
    }

    @Test
    void shouldGetAllMovies() {

        List<Movie> movies = List.of(
                new Movie(1L, "movie1", "SCIFI", 10),
                new Movie(2L, "movie2", "ACTION", 16)
        );

        when(movieRepository.findAll()).thenReturn(movies);

        List<Movie> result = movieService.getAllMovies();

        assertEquals(2, result.size());

        verify(movieRepository).findAll();
    }

    @Test
    void shouldDeleteMovie() {

        doNothing().when(movieRepository).deleteById(1L);

        movieService.deleteMovie(1L);

        verify(movieRepository).deleteById(1L);
    }
}