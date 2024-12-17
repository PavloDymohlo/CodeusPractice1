package org.shad.main;

/**
 * The {@code MovieValidator} class provides utility methods for validating movie titles.
 * <p>
 * A valid movie title:
 * <ul>
 *   <li>Must not be {@code null} or blank.</li>
 *   <li>Must start with an uppercase letter.</li>
 *   <li>Can only contain alphanumeric characters and spaces.</li>
 * </ul>
 * <p>
 * If the title fails to meet any of these conditions, an {@link IllegalArgumentException} is thrown.
 */
public class MovieValidator {
    /**
     * A regular expression pattern to validate movie titles.
     * <p>
     * The title must:
     * <ul>
     *   <li>Start with an uppercase letter.</li>
     *   <li>Contain only letters, numbers, and spaces.</li>
     * </ul>
     */
    // todo: Implement expression
    /**
     * Validates the given movie title.
     * <p>
     * This method checks the following:
     * <ul>
     *   <li>The title is not {@code null} or blank.</li>
     *   <li>The title matches the required format defined by {@code TITLE_REGEX}.</li>
     * </ul>
     *
     * @param title the title of the movie to validate
     * @throws IllegalArgumentException if the title is {@code null}, blank, or does not match the pattern
     */
    public static void validateTitle(String title) {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }
}
