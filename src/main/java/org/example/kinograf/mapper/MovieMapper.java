package org.example.kinograf.mapper;

import org.example.kinograf.DTO.CreateMovieRequest;
import org.example.kinograf.DTO.MovieDTO;
import org.example.kinograf.model.Movie;

public class MovieMapper {

    public static MovieDTO toDTO(Movie movie) {
        return new MovieDTO(
                movie.getMovieId(),
                movie.getName(),
                movie.getOmdbID()

        );
    }

    public static Movie toEntity(CreateMovieRequest request) {
        Movie movie = new Movie();
        movie.setName(request.name());
        movie.setOmdbID(request.omdbID());


        return movie;
    }
}
