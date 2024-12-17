package org.shad.warmup;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * {@link MovieDao} represents a Data Access Object (DAO) for managing movie titles.
 * <p>
 * todo: 1. Implement {@link MovieDao#register(Movie)} that stores a new movie title.
 * todo: 2. Implement {@link MovieDao#findAll()} that returns all movie titles.
 */
public class MovieDao {
    private Set<Movie> movies = new LinkedHashSet<>();

    public boolean register(Movie movie) {
        return movies.add(movie);
        //throw new UnsupportedOperationException(); // todo: Implement this method
    }

    public Set<Movie> findAll() {
        return new LinkedHashSet<>(movies);
        //throw new UnsupportedOperationException(); // todo: Implement this method
    }


    public Movie findByTitle(String title) {
        return movies.stream()
                .filter(movie -> movie.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }

    public boolean removeByTitle(String title) {
        return movies.removeIf(movie -> movie.getTitle().equalsIgnoreCase(title));
    }

    public int countMovies() {
        return movies.size();
    }

    public void clearAll() {
        movies.clear();
    }

    public Set<Movie> findMoviesByYear(int year) {
        Set<Movie> result = new LinkedHashSet<>();
        movies.stream()
                .filter(movie -> movie.getYear() == year)
                .forEach(result::add);
        return result;
    }
}