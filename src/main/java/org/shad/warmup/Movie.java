package org.shad.warmup;

import java.util.Objects;

public class Movie {
    private final String title;
    private final int year;

    public Movie(String title, int year) {
        if (year <= 0) {
            throw new IllegalArgumentException("Year must be a positive integer");
        }
        this.title = title;
        this.year = year;
    }

    // Non-static getter for title
    public String getTitle() {
        return title;
    }

    // Non-static getter for year
    public int getYear() {
        return year;
    }

    public static Movie of(String title, int year) {
        return new Movie(title, year);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return year == movie.year && Objects.equals(title, movie.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, year);
    }

    @Override
    public String toString() {
        return "Movie{title='" + title + "', year=" + year + "}";
    }
}