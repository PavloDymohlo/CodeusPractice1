package org.shad.tuesday.main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shad.tuesday.UpgradeMovieService;
import org.shad.tuesday.warmup.Movie;
import org.shad.tuesday.warmup.MovieDao;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UpgradeMovieServiceTest {
    private UpgradeMovieService upgradeMovieService;

    @BeforeEach
    void setUp() {
        MovieDao movieDao = new MovieDao();
        upgradeMovieService = new UpgradeMovieService(movieDao);

        // Adding sample movies to the DAO
        movieDao.register(new Movie("Inception", 2010));
        movieDao.register(new Movie("Interstellar", 2014));
        movieDao.register(new Movie("Dunkirk", 2017));
        movieDao.register(new Movie("Tenet", 2020));
        movieDao.register(new Movie("Memento", 2000));
    }

    @Test
    void testGetAllMoviesSortedByTitleAscending() {
        List<Movie> sortedMovies = upgradeMovieService.getAllMoviesSorted(true, false);

        assertEquals("Dunkirk", sortedMovies.get(0).getTitle());
        assertEquals("Inception", sortedMovies.get(1).getTitle());
        assertEquals("Interstellar", sortedMovies.get(2).getTitle());
        assertEquals("Memento", sortedMovies.get(3).getTitle());
        assertEquals("Tenet", sortedMovies.get(4).getTitle());
    }

    @Test
    void testGetAllMoviesSortedByTitleDescending() {
        List<Movie> sortedMovies = upgradeMovieService.getAllMoviesSorted(false, false);

        assertEquals("Tenet", sortedMovies.get(0).getTitle());
        assertEquals("Memento", sortedMovies.get(1).getTitle());
        assertEquals("Interstellar", sortedMovies.get(2).getTitle());
        assertEquals("Inception", sortedMovies.get(3).getTitle());
        assertEquals("Dunkirk", sortedMovies.get(4).getTitle());
    }

    @Test
    void testGetAllMoviesSortedByYearAscending() {
        List<Movie> sortedMovies = upgradeMovieService.getAllMoviesSorted(true, true);

        assertEquals(2000, sortedMovies.get(0).getYear());
        assertEquals(2010, sortedMovies.get(1).getYear());
        assertEquals(2014, sortedMovies.get(2).getYear());
        assertEquals(2017, sortedMovies.get(3).getYear());
        assertEquals(2020, sortedMovies.get(4).getYear());
    }

    @Test
    void testGetAllMoviesSortedByYearDescending() {
        List<Movie> sortedMovies = upgradeMovieService.getAllMoviesSorted(false, true);

        assertEquals(2020, sortedMovies.get(0).getYear());
        assertEquals(2017, sortedMovies.get(1).getYear());
        assertEquals(2014, sortedMovies.get(2).getYear());
        assertEquals(2010, sortedMovies.get(3).getYear());
        assertEquals(2000, sortedMovies.get(4).getYear());
    }

    @Test
    void testFilterMoviesByPrefixValid() {
        List<Movie> filteredMovies = upgradeMovieService.filterMoviesByPrefix("In");

        assertEquals(2, filteredMovies.size());
        assertTrue(filteredMovies.contains(new Movie("Inception", 2010)));
        assertTrue(filteredMovies.contains(new Movie("Interstellar", 2014)));
    }

    @Test
    void testFilterMoviesByPrefixCaseInsensitive() {
        List<Movie> filteredMovies = upgradeMovieService.filterMoviesByPrefix("in");

        assertEquals(2, filteredMovies.size());
        assertTrue(filteredMovies.contains(new Movie("Inception", 2010)));
        assertTrue(filteredMovies.contains(new Movie("Interstellar", 2014)));
    }

    @Test
    void testFilterMoviesByPrefixEmptyResult() {
        List<Movie> filteredMovies = upgradeMovieService.filterMoviesByPrefix("XYZ");

        assertTrue(filteredMovies.isEmpty(), "No movies should match the prefix 'XYZ'");
    }

    @Test
    void testFilterMoviesByPrefixWithNull() {
        assertThrows(IllegalArgumentException.class,
                () -> upgradeMovieService.filterMoviesByPrefix(null),
                "Prefix cannot be null or blank.");
    }

    @Test
    void testFilterMoviesByPrefixWithBlank() {
        assertThrows(IllegalArgumentException.class,
                () -> upgradeMovieService.filterMoviesByPrefix(" "),
                "Prefix cannot be null or blank.");
    }

    @Test
    void testFilterMoviesByYearRangeValid() {
        List<Movie> moviesInRange = upgradeMovieService.filterMoviesByYearRange(2000, 2017);

        assertEquals(4, moviesInRange.size());
        assertTrue(moviesInRange.contains(new Movie("Inception", 2010)));
        assertTrue(moviesInRange.contains(new Movie("Interstellar", 2014)));
        assertTrue(moviesInRange.contains(new Movie("Dunkirk", 2017)));
        assertTrue(moviesInRange.contains(new Movie("Memento", 2000)));
    }

    @Test
    void testFilterMoviesByYearRangeEmptyResult() {
        List<Movie> moviesInRange = upgradeMovieService.filterMoviesByYearRange(2025, 2030);

        assertTrue(moviesInRange.isEmpty(), "No movies should be in this year range.");
    }

    @Test
    void testFilterMoviesByYearRangeWithInvalidRange() {
        assertThrows(IllegalArgumentException.class,
                () -> upgradeMovieService.filterMoviesByYearRange(2020, 2000),
                "Start year cannot be greater than end year.");
    }

}