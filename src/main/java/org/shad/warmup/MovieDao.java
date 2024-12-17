package org.shad.warmup;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * {@code MovieDao} represents a Data Access Object (DAO) for managing movies.
 * <p>
 * This class provides methods to perform operations such as:
 * <ul>
 *     <li>Registering a new movie</li>
 *     <li>Retrieving all movies</li>
 *     <li>Finding, removing, and filtering movies by title or year</li>
 *     <li>Counting and clearing movies</li>
 * </ul>
 * The underlying data structure is a {@link LinkedHashSet} to maintain insertion order
 * and ensure uniqueness of movies.
 */
public class MovieDao {
    private final Set<Movie> movies = new LinkedHashSet<>();

    /**
     * Registers a new movie.
     * <p>
     * Adds the movie to the collection. If a movie with the same title and year already exists,
     * it will not be added as the {@link LinkedHashSet} ensures uniqueness.
     *
     * @param movie the movie to register
     * @return {@code true} if the movie was successfully added, {@code false} if it already exists
     */
    public boolean register(Movie movie) {
        return movies.add(movie);
        //throw new UnsupportedOperationException(); // todo: Implement this method
    }

    /**
     * Retrieves all registered movies.
     * <p>
     * The method returns a new {@link LinkedHashSet} to preserve immutability of the internal collection.
     *
     * @return a set of all movies
     */
    public Set<Movie> findAll() {
        return new LinkedHashSet<>(movies);
        //throw new UnsupportedOperationException(); // todo: Implement this method
    }

    /**
     * Finds a movie by its title.
     * <p>
     * The search is case-insensitive.
     *
     * @param title the title of the movie to find
     * @return the movie with the matching title, or {@code null} if no match is found
     */
    public Movie findByTitle(String title) {
        return movies.stream()
                .filter(movie -> movie.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }

    /**
     * Removes a movie by its title.
     * <p>
     * The removal is case-insensitive. If multiple movies have the same title, all will be removed.
     *
     * @param title the title of the movie to remove
     * @return {@code true} if any movies were removed, {@code false} otherwise
     */
    public boolean removeByTitle(String title) {
        return movies.removeIf(movie -> movie.getTitle().equalsIgnoreCase(title));
    }

    /**
     * Counts the total number of registered movies.
     *
     * @return the total count of movies
     */
    public int countMovies() {
        return movies.size();
    }

    /**
     * Clears all registered movies from the collection.
     */
    public void clearAll() {
        movies.clear();
    }


    /**
     * Finds all movies released in a specific year.
     *
     * @param year the release year to filter movies
     * @return a set of movies released in the specified year
     */
    public Set<Movie> findMoviesByYear(int year) {
        Set<Movie> result = new LinkedHashSet<>();
        movies.stream()
                .filter(movie -> movie.getYear() == year)
                .forEach(result::add);
        return result;
    }
}