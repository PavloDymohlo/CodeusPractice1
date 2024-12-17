package org.shad.main;

public class MovieValidator {
    private static final String TITLE_REGEX = "^[A-Z][A-Za-z0-9 ]*$";

    public static void validateTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or blank");
        }
        if (!title.matches(TITLE_REGEX)) {
            throw new IllegalArgumentException("Invalid movie title: " + title);
        }
    }
}
