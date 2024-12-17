package org.shad.warmup;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MovieDaoTest {

    @Test
    void testRegisterMovie() {
        MovieDao dao = new MovieDao();
        Movie inception = new Movie("Inception", 2010);

        assertTrue(dao.register(inception), "Movie should be successfully registered");
        assertFalse(dao.register(inception), "Duplicate movie should not be registered");
    }

    @Test
    void testFindAllMovies() {
        MovieDao dao = new MovieDao();
        dao.register(new Movie("Inception", 2010));
        dao.register(new Movie("Interstellar", 2014));

        Set<Movie> movies = dao.findAll();

        assertEquals(2, movies.size(), "There should be 2 movies in the DAO");
        assertTrue(movies.contains(new Movie("Inception", 2010)));
        assertTrue(movies.contains(new Movie("Interstellar", 2014)));
    }

    @Test
    void testFindByTitle() {
        MovieDao dao = new MovieDao();
        dao.register(new Movie("Inception", 2010));
        dao.register(new Movie("Dunkirk", 2017));

        Movie foundMovie = dao.findByTitle("Inception");
        assertNotNull(foundMovie, "Movie should be found by title");
        assertEquals("Inception", foundMovie.getTitle());
        assertEquals(2010, foundMovie.getYear());

        Movie notFoundMovie = dao.findByTitle("Tenet");
        assertNull(notFoundMovie, "Movie not in the DAO should return null");
    }

    @Test
    void testRemoveByTitle() {
        MovieDao dao = new MovieDao();
        dao.register(new Movie("Inception", 2010));
        dao.register(new Movie("Tenet", 2020));

        assertTrue(dao.removeByTitle("Inception"), "Movie should be removed successfully");
        assertNull(dao.findByTitle("Inception"), "Removed movie should no longer exist");
        assertFalse(dao.removeByTitle("Inception"), "Removing a non-existent movie should return false");
    }

    @Test
    void testCountMovies() {
        MovieDao dao = new MovieDao();
        assertEquals(0, dao.countMovies(), "Count should be 0 for an empty DAO");

        dao.register(new Movie("Inception", 2010));
        dao.register(new Movie("Tenet", 2020));

        assertEquals(2, dao.countMovies(), "Count should reflect the number of registered movies");
    }

    @Test
    void testClearAll() {
        MovieDao dao = new MovieDao();
        dao.register(new Movie("Inception", 2010));
        dao.register(new Movie("Tenet", 2020));

        dao.clearAll();

        assertEquals(0, dao.countMovies(), "All movies should be cleared");
        assertTrue(dao.findAll().isEmpty(), "findAll should return an empty set after clearAll");
    }

    @Test
    void testFindMoviesByYear() {
        MovieDao dao = new MovieDao();
        dao.register(new Movie("Inception", 2010));
        dao.register(new Movie("Interstellar", 2014));
        dao.register(new Movie("Dunkirk", 2017));
        dao.register(new Movie("Tenet", 2020));
        dao.register(new Movie("Another Movie", 2010));

        Set<Movie> movies2010 = dao.findMoviesByYear(2010);
        assertEquals(2, movies2010.size(), "There should be 2 movies from the year 2010");
        assertTrue(movies2010.contains(new Movie("Inception", 2010)));
        assertTrue(movies2010.contains(new Movie("Another Movie", 2010)));

        Set<Movie> movies2020 = dao.findMoviesByYear(2020);
        assertEquals(1, movies2020.size(), "There should be 1 movie from the year 2020");
        assertTrue(movies2020.contains(new Movie("Tenet", 2020)));

        Set<Movie> moviesUnknownYear = dao.findMoviesByYear(1999);
        assertTrue(moviesUnknownYear.isEmpty(), "No movies should be found for a year with no entries");
    }
}