package org.shad.thursday.warmup;

/**
 * Represents an HTTP header with a name and value.
 * This class provides utilities for creating and parsing HTTP headers.
 * The header name is normalized to lowercase, and both the name and value are trimmed of leading and trailing whitespace.
 * Example Usage:
 * <pre>
 *     HTTPHeader header = HTTPHeader.of("Content-Type", "application/json");
 *     HTTPHeader parsedHeader = HTTPHeader.parse("Content-Type: application/json");
 * </pre>
 */
public class HTTPHeader {

    /**
     * Private constructor to initialize an HTTP header.
     *
     * @param name  the name of the header (case-insensitive)
     * @param value the value of the header
     * @throws IllegalArgumentException if the name is null, empty, or if the value is null
     */
    private HTTPHeader(String name, String value) {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }

    /**
     * Creates a new HTTPHeader instance with the specified name and value.
     *
     * @param name  the name of the header (case-insensitive)
     * @param value the value of the header
     * @return a new {@link HTTPHeader} instance
     * @throws IllegalArgumentException if the name is null, empty, or if the value is null
     */
    public static HTTPHeader of(String name, String value) {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }

    /**
     * Parses a raw HTTP header string into an {@link HTTPHeader} instance.
     * The raw header must be in the format "name: value". The method trims leading and trailing
     * whitespace from the name and value, and normalizes the name to lowercase.
     *
     * @param rawHeader the raw HTTP header string to parse
     * @return a new {@link HTTPHeader} instance
     * @throws IllegalArgumentException if the raw header is null, does not contain a colon,
     *                                  or if the name or value is invalid
     */
    public static HTTPHeader parse(String rawHeader) {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }

    /**
     * Gets the name of the HTTP header.
     *
     * @return the name of the header, normalized to lowercase
     */
    public String getName() {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }

    /**
     * Gets the value of the HTTP header.
     *
     * @return the value of the header
     */
    public String getValue() {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }
}
