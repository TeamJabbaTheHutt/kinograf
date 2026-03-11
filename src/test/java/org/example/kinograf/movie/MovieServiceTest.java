package org.example.kinograf.movie;

import org.example.kinograf.DTO.createRequest.CreateMovieRequest;
import org.example.kinograf.DTO.MovieDTO;
import org.example.kinograf.model.Movie;
import org.example.kinograf.repository.MovieRepository;
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
        // Arrange
        CreateMovieRequest request = new CreateMovieRequest("movieName", "tt1234567");

        Movie movie = new Movie();
        movie.setMovieId(1L);
        movie.setName("movieName");
        movie.setOmdbID("tt1234567");

        when(movieRepository.save(any(Movie.class))).thenReturn(movie);

        // Act
        MovieDTO movieDTO = movieService.createMovie(
                request.name(),
                request.omdbID()
        );

        // Assert
        assertNotNull(movieDTO);
        assertEquals("movieName", movieDTO.name());
        assertEquals("tt1234567", movieDTO.omdbID());

        verify(movieRepository).save(any(Movie.class));
    }

    @Test
    void shouldUpdateMovie() {
        // Arrange
        Movie movie = new Movie();
        movie.setMovieId(1L);
        movie.setName("movieName");
        movie.setOmdbID("tt1111111");

        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));
        when(movieRepository.save(any(Movie.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        MovieDTO updatedDTO = movieService.updateMovie(1L, "newMovie", "tt2222222");

        // Assert
        assertNotNull(updatedDTO);
        assertEquals("newMovie", updatedDTO.name());
        assertEquals("tt2222222", updatedDTO.omdbID());

        verify(movieRepository).save(movie);
    }

    @Test
    void shouldGetAllMovies() {
        // Arrange
        Movie m1 = new Movie();
        m1.setMovieId(1L);
        m1.setName("movieName1");
        m1.setOmdbID("tt1111111");

        Movie m2 = new Movie();
        m2.setMovieId(2L);
        m2.setName("movieName2");
        m2.setOmdbID("tt2222222");

        when(movieRepository.findAll()).thenReturn(List.of(m1, m2));

        // Act
        List<MovieDTO> result = movieService.getAllMovies();

        // Assert
        assertEquals(2, result.size());

        assertEquals("movieName1", result.get(0).name());
        assertEquals("tt1111111", result.get(0).omdbID());

        assertEquals("movieName2", result.get(1).name());
        assertEquals("tt2222222", result.get(1).omdbID());

        verify(movieRepository).findAll();
    }

    @Test
    void shouldDeleteMovie() {
        // Arrange
        doNothing().when(movieRepository).deleteById(1L);

        // Act
        movieService.deleteMovie(1L);

        // Assert
        verify(movieRepository).deleteById(1L);
    }
}