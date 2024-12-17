package org.shad.main;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieValidatorTest {
    @Test
    void testValidTitle() {
        assertDoesNotThrow(() -> MovieValidator.validateTitle("Inception"),
                "Valid title should not throw an exception");
        assertDoesNotThrow(() -> MovieValidator.validateTitle("The Matrix"),
                "Valid title with spaces should not throw an exception");
        assertDoesNotThrow(() -> MovieValidator.validateTitle("Avatar2"),
                "Valid title with numbers should not throw an exception");
    }

    @Test
    void testNullTitle() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> MovieValidator.validateTitle(null),
                "Null title should throw an exception");
        assertEquals("Title cannot be null or blank", exception.getMessage());
    }

    @Test
    void testBlankTitle() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> MovieValidator.validateTitle(""),
                "Blank title should throw an exception");
        assertEquals("Title cannot be null or blank", exception.getMessage());
    }

    @Test
    void testTitleWithLeadingLowercase() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> MovieValidator.validateTitle("inception"),
                "Title starting with lowercase letter should throw an exception");
        assertEquals("Invalid movie title: inception", exception.getMessage());
    }

    @Test
    void testTitleWithSpecialCharacters() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> MovieValidator.validateTitle("Inception!"),
                "Title with special characters should throw an exception");
        assertEquals("Invalid movie title: Inception!", exception.getMessage());
    }

    @Test
    void testTitleWithOnlyWhitespace() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> MovieValidator.validateTitle("   "),
                "Title with only whitespace should throw an exception");
        assertEquals("Title cannot be null or blank", exception.getMessage());
    }

    @Test
    void testTitleWithMinimumLength() {
        assertDoesNotThrow(() -> MovieValidator.validateTitle("A"),
                "Title with minimum valid length should not throw an exception");
    }

    @Test
    void testTitleWithExcessiveLength() {
        String longTitle = "A".repeat(100); // Long title with valid format
        assertDoesNotThrow(() -> MovieValidator.validateTitle(longTitle),
                "Very long but valid title should not throw an exception");
    }

    @Test
    void testTitleWithMultipleSpaces() {
        assertDoesNotThrow(() -> MovieValidator.validateTitle("The Lord Of The Rings"),
                "Title with multiple spaces should not throw an exception");
    }

    @Test
    void testTitleWithNumbersAndSpaces() {
        assertDoesNotThrow(() -> MovieValidator.validateTitle("Star Wars 4"),
                "Title with numbers and spaces should not throw an exception");
    }

    @Test
    void testTitleWithInvalidCharacterAtStart() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> MovieValidator.validateTitle("@Inception"),
                "Title starting with special character should throw an exception");
        assertEquals("Invalid movie title: @Inception", exception.getMessage());
    }

}