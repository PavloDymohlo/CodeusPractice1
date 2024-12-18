package org.shad.tuesday.warmup;

import org.junit.jupiter.api.Test;
import org.shad.tuesday.warmup.Movie;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    @Test
    void testMovieEquality() {
        Movie movie1 = new Movie("Inception", 2010);
        Movie movie2 = new Movie("Inception", 2010);
        Movie movie3 = new Movie("Interstellar", 2014);

        assertEquals(movie1, movie2, "Movies with same title and year should be equal");
        assertNotEquals(movie1, movie3, "Movies with different titles or years should not be equal");
    }

    @Test
    void testHashCode() {
        Movie movie1 = new Movie("Inception", 2010);
        Movie movie2 = new Movie("Inception", 2010);

        assertEquals(movie1.hashCode(), movie2.hashCode(),
                "Hashcode of equal movies should be the same");

        Movie movie3 = new Movie("Interstellar", 2014);
        assertNotEquals(movie1.hashCode(), movie3.hashCode(),
                "Hashcode of different movies should not be the same");
    }

    @Test
    void testToString() {
        Movie movie = new Movie("Inception", 2010);
        String expected = "Movie{title='Inception', year=2010}";

        assertEquals(expected, movie.toString(), "toString should return the correct format");
    }

    @Test
    void testGetters() {
        Movie movie = new Movie("Tenet", 2020);

        assertEquals("Tenet", movie.getTitle(), "getTitle should return the correct title");
        assertEquals(2020, movie.getYear(), "getYear should return the correct year");
    }

    @Test
    void testInvalidYear() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Movie("Inception", -1), "Year cannot be negative");
        assertEquals("Year must be a positive integer", exception.getMessage());
    }

    @Test
    void testStaticFactoryMethod() {
        Movie movie = Movie.of("Inception", 2010);

        assertNotNull(movie, "Static factory method should create a movie instance");
        assertEquals("Inception", movie.getTitle(), "Static factory method should set the correct title");
        assertEquals(2010, movie.getYear(), "Static factory method should set the correct year");
    }

    @Test
    void testEqualsWithDifferentTypes() {
        Movie movie = new Movie("Inception", 2010);

        assertNotEquals(null, movie, "Movie should not be equal to null");
        assertNotEquals(new Object(), movie, "Movie should not be equal to a generic object");
}

    @Test
    void testCompareToWithSameTitles() {
        Movie movie1 = new Movie("Inception", 2010);
        Movie movie2 = new Movie("Inception", 2014);

        assertEquals(0, movie1.getTitle().compareTo(movie2.getTitle()),
                "Movies with the same title should compare equally");
    }

    @Test
    void testCompareToWithDifferentTitles() {
        Movie movie1 = new Movie("Inception", 2010);
        Movie movie2 = new Movie("Interstellar", 2014);

        assertTrue(movie1.getTitle().compareTo(movie2.getTitle()) < 0,
                "Movies should compare lexicographically by title");
    }

}