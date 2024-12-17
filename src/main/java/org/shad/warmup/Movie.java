package org.shad.warmup;

import java.util.Objects;

/**
 * Represents a movie with a title and a release year.
 * <p>
 * Instances of this class are immutable. Title and year are set through the constructor
 * and cannot be changed after creation.
 */
public class Movie {
    private final String title;
    private final int year;

    /**
     * Constructs a {@code Movie} with the specified title and release year.
     * <p>
     * The year must be a positive integer.
     *
     * @param title the title of the movie
     * @param year  the release year of the movie
     * @throws IllegalArgumentException if the year is not a positive integer
     */
    public Movie(String title, int year) {
        if (year <= 0) {
            throw new IllegalArgumentException("Year must be a positive integer");
        }
        this.title = title;
        this.year = year;
    }

    /**
     * Returns the title of the movie.
     *
     * @return the title of the movie
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the release year of the movie.
     *
     * @return the release year of the movie
     */
    public int getYear() {
        return year;
    }

    /**
     * A factory method to create a new {@code Movie} instance.
     *
     * @param title the title of the movie
     * @param year  the release year of the movie
     * @return a new {@code Movie} instance with the specified title and year
     * @throws IllegalArgumentException if the year is not a positive integer
     */
    public static Movie of(String title, int year) {
        return new Movie(title, year);
    }

    /**
     * Indicates whether this movie is "equal to" another object.
     * <p>
     * Two movies are considered equal if they have the same title and release year.
     *
     * @param o the reference object with which to compare
     * @return {@code true} if this object is equal to the specified object; {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return year == movie.year && Objects.equals(title, movie.title);
    }

    /**
     * Returns the hash code value for this movie.
     * <p>
     * The hash code is calculated based on the movie's title and year.
     *
     * @return the hash code value for this movie
     */
    @Override
    public int hashCode() {
        return Objects.hash(title, year);
    }

    /**
     * Returns a string representation of the movie.
     * <p>
     * The string includes the movie's title and release year.
     *
     * @return a string representation of the movie in the format "Movie{title='...', year=...}"
     */
    @Override
    public String toString() {
        return "Movie{title='" + title + "', year=" + year + "}";
    }
}