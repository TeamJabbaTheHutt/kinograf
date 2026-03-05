package org.example.kinograf.movie;

import org.example.kinograf.DTO.CreateMovieRequest;
import org.example.kinograf.DTO.MovieDTO;
import org.example.kinograf.DTO.UpdateMovieRequest;
import org.example.kinograf.mapper.MovieMapper;
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

        MovieDTO movieDTO = movieService.createMovie(
                "movieName",
                "SCIFI",
                10
        );

        CreateMovieRequest request = new CreateMovieRequest("NewMovieName", "SCIFI", 10);
        Long id = 1L;
        Movie movie = MovieMapper.toEntity(request);

        when(movieRepository.save(any())).thenReturn(movie);

        assertNotNull(movie);
        assertEquals("movieName", movie.getName());
        assertEquals("SCIFI", movie.getCategories());
        assertEquals(10, movie.getAgeLimit());

        verify(movieRepository).save(any());
    }

    @Test
    void shouldUpdateMovie() {
        CreateMovieRequest request = new CreateMovieRequest("movieName", "SCIFI", 10);
        Long id = 1L;



        Movie movie = MovieMapper.toEntity(request);

        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));
        when(movieRepository.save(any())).thenReturn(movie);



        assertEquals("newMovie", movie.getName());
        assertEquals("ACTION", movie.getCategories());
        assertEquals(15, movie.getAgeLimit());

        verify(movieRepository).save(movie);
    }

    @Test
    void shouldGetAllMovies() {
        Movie m1 = new Movie();
        m1.setName("movieName1");
        m1.setCategories("SCIFI");
        m1.setAgeLimit(10);
        Movie m2 = new Movie();
        m2.setName("movieName2");
        m2.setCategories("THRILLER");
        m2.setAgeLimit(10);



        List<Movie> movies = List.of(
                m1,
                m2
        );

        when(movieRepository.findAll()).thenReturn(movies);

        List<MovieDTO> result = movieService.getAllMovies();

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