package org.example.kinograf.service;

import org.example.kinograf.DTO.MovieDTO;
import org.example.kinograf.mapper.MovieMapper;
import org.example.kinograf.model.Movie;
import org.example.kinograf.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieDTO> getAllMovies() {
        return movieRepository.findAll()
                .stream()
                .map(MovieMapper::toDTO)
                .toList();
    }

    public Optional<MovieDTO> getMovieById(Long id) {
        Optional<Movie> movie = movieRepository.findById(id);

        if (movie.isPresent()) {
            return Optional.of(MovieMapper.toDTO(movie.get()));
        }

        return Optional.empty();
    }

    public MovieDTO createMovie(
            String name,
            String categories,
            int ageLimit
    ) {
        Movie movie = new Movie();

        movie.setName(name);
        movie.setCategories(categories);
        movie.setAgeLimit(ageLimit);

        return MovieMapper.toDTO(movieRepository.save(movie));
    }

    public MovieDTO updateMovie(
            Long movieId,
            String name,
            String categories,
            int ageLimit
    ) {
        Movie updated = movieRepository.findById(movieId).orElse(null);

        if (updated == null) {
            return null;
        }

        updated.setName(name);
        updated.setCategories(categories);
        updated.setAgeLimit(ageLimit);

        return MovieMapper.toDTO(movieRepository.save(updated));
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}