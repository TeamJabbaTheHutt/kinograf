package org.example.kinograf.config;

import org.example.kinograf.model.Movie;
import org.example.kinograf.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitApp implements CommandLineRunner {
    private final MovieRepository movieRepository;

    public InitApp(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Movie movie1 = new Movie();
        movie1.setName("Dune");
        movie1.setCategories("Sci-Fi");
        movie1.setAgeLimit(15);

        Movie movie2 = new Movie();
        movie2.setName("Batman");
        movie2.setCategories("Action");
        movie2.setAgeLimit(13);

        Movie movie3 = new Movie();
        movie3.setName("Frozen");
        movie3.setCategories("Animation");
        movie3.setAgeLimit(7);

        movieRepository.save(movie1);
        movieRepository.save(movie2);
        movieRepository.save(movie3);

        System.out.println("Movies added to H2 database");
    }
}


