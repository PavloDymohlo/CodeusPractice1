package org.shad.tuesday.main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shad.tuesday.MovieService;
import org.shad.tuesday.warmup.Movie;
import org.shad.tuesday.warmup.MovieDao;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieServiceTest {
    private MovieService movieService;
    private MovieDao movieDao;

    @BeforeEach
    void setUp() {
        movieDao = new MovieDao();
        movieService = new MovieService(movieDao);
    }

    /**
     * Tests for registerMovie()
     */
    @Test
    void testRegisterValidMovie() {
        Movie movie = new Movie("Inception", 2010);
        assertTrue(movieService.registerMovie(movie));
        assertEquals(1, movieDao.countMovies(), "Movie count should be 1 after registration.");
    }

    @Test
    void testRegisterDuplicateMovie() {
        Movie movie = new Movie("Inception", 2010);
        movieService.registerMovie(movie);
        assertFalse(movieService.registerMovie(movie), "Duplicate movie should not be registered.");
    }

    @Test
    void testRegisterMovieWithInvalidTitle() {
        Movie invalidMovie = new Movie(" inception", 2010);
        assertThrows(IllegalArgumentException.class,
                () -> movieService.registerMovie(invalidMovie),
                "Movie title starting with a lowercase letter should throw an exception.");
    }

    @Test
    void testRegisterMovieWithBlankTitle() {
        Movie invalidMovie = new Movie(" ", 2010);
        assertThrows(IllegalArgumentException.class,
                () -> movieService.registerMovie(invalidMovie),
                "Blank movie title should throw an exception.");
    }

    @Test
    void testRegisterMovieWithNullTitle() {
        Movie invalidMovie = new Movie(null, 2010);
        assertThrows(IllegalArgumentException.class,
                () -> movieService.registerMovie(invalidMovie),
                "Null movie title should throw an exception.");
    }

    /**
     * Tests for searchMovies()
     */
    @Test
    void testSearchMoviesWithValidQuery() {
        movieService.registerMovie(new Movie("Inception", 2010));
        movieService.registerMovie(new Movie("Interstellar", 2014));
        movieService.registerMovie(new Movie("Dunkirk", 2017));

        List<Movie> result = movieService.searchMovies("In");
        assertEquals(2, result.size(), "Should return movies that contain 'In' in their title.");
    }

    @Test
    void testSearchMoviesWithNoMatches() {
        movieService.registerMovie(new Movie("Inception", 2010));
        List<Movie> result = movieService.searchMovies("Avatar");
        assertTrue(result.isEmpty(), "Search query with no matches should return an empty list.");
    }

    @Test
    void testSearchMoviesWithNullQuery() {
        assertThrows(IllegalArgumentException.class,
                () -> movieService.searchMovies(null),
                "Null query should throw an exception.");
    }

    @Test
    void testSearchMoviesWithBlankQuery() {
        assertThrows(IllegalArgumentException.class,
                () -> movieService.searchMovies(" "),
                "Blank query should throw an exception.");
    }

    /**
     * Tests for filterByLength()
     */
    @Test
    void testFilterByLengthWithValidLength() {
        movieService.registerMovie(new Movie("Up", 2009));
        movieService.registerMovie(new Movie("Avatar", 2009));
        movieService.registerMovie(new Movie("Inception", 2010));

        List<Movie> result = movieService.filterByLength(5);
        assertEquals(2, result.size(), "Movies with title length >= 5 should be returned.");
        assertTrue(result.stream().anyMatch(movie -> movie.getTitle().equals("Avatar")));
        assertTrue(result.stream().anyMatch(movie -> movie.getTitle().equals("Inception")));
    }

    @Test
    void testFilterByLengthWithNoMatches() {
        movieService.registerMovie(new Movie("Up", 2009));
        List<Movie> result = movieService.filterByLength(10);
        assertTrue(result.isEmpty(), "No movies should match the length filter.");
    }

    @Test
    void testFilterByLengthWithNegativeLength() {
        assertThrows(IllegalArgumentException.class,
                () -> movieService.filterByLength(-1),
                "Negative length should throw an exception.");
    }

    /**
     * Additional Test Cases
     */
    @Test
    void testGetMovieDao() {
        assertNotNull(movieService.getMovieDao(), "MovieDao should not be null.");
        assertSame(movieDao, movieService.getMovieDao(), "getMovieDao should return the same instance.");
    }

    @Test
    void testFilterByLengthWithZero() {
        movieService.registerMovie(new Movie("Up", 2009));
        List<Movie> result = movieService.filterByLength(0);
        assertEquals(1, result.size(), "All movies should be returned for minimum length 0.");
    }
}