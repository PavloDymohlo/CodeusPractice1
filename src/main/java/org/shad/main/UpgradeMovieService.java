package org.shad.main;

import org.shad.warmup.Movie;
import org.shad.warmup.MovieDao;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class UpgradeMovieService {

    public UpgradeMovieService(MovieDao movieDao) {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }


    /**
     * Retrieves all movies sorted either in ascending or descending order.
     *
     * @param ascending flag to determine sorting order
     * @param sortByYear flag to determine if sorting is by year or title
     * @return sorted list of movies
     */
    public List<Movie> getAllMoviesSorted(boolean ascending, boolean sortByYear) {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }

    /**
     * Retrieves movies filtered by title prefix (case-insensitive).
     *
     * @param prefix the prefix to filter movie titles
     * @return list of movies matching the prefix
     */
    public List<Movie> filterMoviesByPrefix(String prefix) {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }

    /**
     * Retrieves movies filtered by a range of years (inclusive).
     *
     * @param startYear the starting year (inclusive)
     * @param endYear   the ending year (inclusive)
     * @return list of movies released within the specified range
     */
    public List<Movie> filterMoviesByYearRange(int startYear, int endYear) {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }

}