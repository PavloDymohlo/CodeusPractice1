package org.shad.main;

import org.shad.warmup.Movie;
import org.shad.warmup.MovieDao;

import java.util.List;
import java.util.stream.Collectors;

/**
 * {@link MovieService} provides operations to manage and filter movie titles.
 * <p>
 * todo: 1. Implement {@link MovieService#registerMovie(Movie)} using {@link MovieDao}.
 * todo: 2. Implement {@link MovieService#searchMovies(String)} to search movie titles.
 * todo: 3. Implement {@link MovieService#filterByLength(int)} to filter movies by title length.
 */
public class MovieService {
    private final MovieDao movieDao;

    public MovieService(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    public boolean registerMovie(Movie movie) {
        MovieValidator.validateTitle(movie.getTitle());
        return movieDao.register(movie);
        //throw new UnsupportedOperationException(); // todo: Implement this method
    }

    public List<Movie> searchMovies(String query) {
        if (query == null || query.isBlank()) {
            throw new IllegalArgumentException("Search query cannot be null or blank");
        }
        return movieDao.findAll().stream()
                .filter(movie -> movie.getTitle().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
        //throw new UnsupportedOperationException(); // todo: Implement this method
    }

    public List<Movie> filterByLength(int minLength) {
        if (minLength < 0) {
            throw new IllegalArgumentException("Minimum length cannot be negative");
        }
        return movieDao.findAll().stream()
                .filter(movie -> movie.getTitle().length() >= minLength)
                .collect(Collectors.toList());
        // throw new UnsupportedOperationException(); // todo: Implement this method
    }

    public MovieDao getMovieDao() {
        return movieDao;
    }
}
