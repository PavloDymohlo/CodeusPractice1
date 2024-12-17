package org.shad.main;

import org.shad.warmup.Movie;
import org.shad.warmup.MovieDao;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class UpgradeMovieService extends MovieService {

    public UpgradeMovieService(MovieDao movieDao) {
        super(movieDao);
    }


    /**
     * Retrieves all movies sorted either in ascending or descending order.
     *
     * @param ascending flag to determine sorting order
     * @param sortByYear flag to determine if sorting is by year or title
     * @return sorted list of movies
     */
    public List<Movie> getAllMoviesSorted(boolean ascending, boolean sortByYear) {
        Comparator<Movie> comparator = sortByYear
                ? Comparator.comparing(Movie::getYear)   // Sort by year
                : Comparator.comparing(Movie::getTitle); // Sort by title

        // Apply descending order if not ascending
        if (!ascending) {
            comparator = comparator.reversed();
        }

        return getMovieDao().findAll().stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves movies filtered by title prefix (case-insensitive).
     *
     * @param prefix the prefix to filter movie titles
     * @return list of movies matching the prefix
     */
    public List<Movie> filterMoviesByPrefix(String prefix) {
        if (prefix == null || prefix.isBlank()) {
            throw new IllegalArgumentException("Prefix cannot be null or blank.");
        }

        return getMovieDao().findAll().stream()
                .filter(movie -> movie.getTitle()
                        .toLowerCase()
                        .startsWith(prefix.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves movies filtered by a range of years (inclusive).
     *
     * @param startYear the starting year (inclusive)
     * @param endYear   the ending year (inclusive)
     * @return list of movies released within the specified range
     */
    public List<Movie> filterMoviesByYearRange(int startYear, int endYear) {
        if (startYear > endYear) {
            throw new IllegalArgumentException("Start year cannot be greater than end year.");
        }

        return getMovieDao().findAll().stream()
                .filter(movie -> movie.getYear() >= startYear && movie.getYear() <= endYear)
                .collect(Collectors.toList());
    }

}