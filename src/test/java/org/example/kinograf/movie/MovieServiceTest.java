package org.example.kinograf.movie;

import org.example.kinograf.DTO.CreateMovieRequest;
import org.example.kinograf.DTO.MovieDTO;
import org.example.kinograf.DTO.UpdateMovieRequest;
import org.example.kinograf.mapper.MovieMapper;
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
        CreateMovieRequest request = new CreateMovieRequest("movieName", "SCIFI", 10);
        Movie movie = MovieMapper.toEntity(request);
        movie.setMovieId(1L);

        when(movieRepository.save(any(Movie.class))).thenReturn(movie);

        // Act
        MovieDTO movieDTO = movieService.createMovie(
                request.name(),
                request.categories(),
                request.ageLimit()
        );

        // Assert
        assertNotNull(movieDTO);
        assertEquals("movieName", movieDTO.name());
        assertEquals("SCIFI", movieDTO.categories());
        assertEquals(10, movieDTO.ageLimit());

        verify(movieRepository).save(any(Movie.class));
    }

    @Test
    void shouldUpdateMovie() {
        // Arrange
        Movie movie = new Movie("movieName", "SCIFI", 10);
        movie.setMovieId(1L);

        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));
        when(movieRepository.save(any(Movie.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        MovieDTO updatedDTO = movieService.updateMovie(1L, "newMovie", "ACTION", 15);

        // Assert
        assertNotNull(updatedDTO);
        assertEquals("newMovie", updatedDTO.name());
        assertEquals("ACTION", updatedDTO.categories());
        assertEquals(15, updatedDTO.ageLimit());

        verify(movieRepository).save(movie);
    }

    @Test
    void shouldGetAllMovies() {
        // Arrange
        Movie m1 = new Movie("movieName1", "SCIFI", 10);
        Movie m2 = new Movie("movieName2", "THRILLER", 12);

        when(movieRepository.findAll()).thenReturn(List.of(m1, m2));

        // Act
        List<MovieDTO> result = movieService.getAllMovies();

        // Assert
        assertEquals(2, result.size());
        assertEquals("movieName1", result.get(0).name());
        assertEquals("movieName2", result.get(1).name());

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