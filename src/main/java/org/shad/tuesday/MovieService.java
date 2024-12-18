package org.shad.tuesday;

import org.shad.tuesday.warmup.Movie;
import org.shad.tuesday.warmup.MovieDao;

import java.util.List;

/**
 * {@code MovieService} provides operations to manage and filter movie titles.
 * <p>
 * It supports functionalities such as:
 * <ul>
 *     <li>Registering movies with title validation</li>
 *     <li>Searching for movies by title</li>
 *     <li>Filtering movies by title length</li>
 * </ul>
 * <p>
 * The class relies on a {@link MovieDao} implementation for data persistence and retrieval.
 */
public class MovieService {

    /**
     * Constructs a {@code MovieService} with a specified {@link MovieDao}.
     *
     * @param movieDao the data access object responsible for managing movie records
     */
    public MovieService(MovieDao movieDao) {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }

    /**
     * Registers a movie after validating its title.
     * <p>
     * The method uses {@link MovieValidator} to ensure that the movie title meets validation rules.
     *
     * @param movie the movie to be registered
     * @return {@code true} if the movie was successfully registered, {@code false} otherwise
     * @throws IllegalArgumentException if the movie title is invalid
     */
    public boolean registerMovie(Movie movie) {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }

    /**
     * Searches for movies whose titles contain the specified query string (case-insensitive).
     *
     * @param query the search query to match against movie titles
     * @return a list of movies whose titles contain the specified query
     * @throws IllegalArgumentException if the query is {@code null} or blank
     */
    public List<Movie> searchMovies(String query) {
        // todo: Implement this method
        throw new UnsupportedOperationException();    }

    /**
     * Filters movies whose titles have a length greater than or equal to the specified minimum length.
     *
     * @param minLength the minimum length of the movie titles
     * @return a list of movies with titles meeting the specified length criteria
     * @throws IllegalArgumentException if {@code minLength} is negative
     */
    public List<Movie> filterByLength(int minLength) {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }
    /**
     * Retrieves the {@link MovieDao} instance associated with this service.
     *
     * @return the {@code MovieDao} used for movie data management
     */
    public MovieDao getMovieDao() {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }
}
