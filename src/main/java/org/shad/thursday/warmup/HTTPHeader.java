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
    private final String name;
    private final String value;

    /**
     * Private constructor to initialize an HTTP header.
     *
     * @param name  the name of the header (case-insensitive)
     * @param value the value of the header
     * @throws IllegalArgumentException if the name is null, empty, or if the value is null
     */
    private HTTPHeader(String name, String value) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Header name cannot be empty or null");
        }
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }
        this.name = name.trim().toLowerCase();
        this.value = value != null ? value.trim() : "";
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
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Header name cannot be null or empty");
        }
        if (value == null) {
            throw new IllegalArgumentException("Header value cannot be null");
        }
        return new HTTPHeader(name, value.trim());
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
        if (rawHeader == null || rawHeader.isBlank()) {
            throw new IllegalArgumentException("Invalid header format: Header cannot be null or blank");
        }

        if (!rawHeader.contains(":")) {
            throw new IllegalArgumentException("Invalid header format: " + rawHeader);
        }

        String[] parts = rawHeader.split(":", 2);
        String name = parts[0].trim();
        String value = parts.length > 1 ? parts[1].trim() : "";

        if (name.isEmpty()) {
            throw new IllegalArgumentException("Header name cannot be null or empty");
        }

        if (value.isEmpty()) {
            throw new IllegalArgumentException("Header value cannot be null");
        }

        return new HTTPHeader(name, value);
    }

    /**
     * Gets the name of the HTTP header.
     *
     * @return the name of the header, normalized to lowercase
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the value of the HTTP header.
     *
     * @return the value of the header
     */
    public String getValue() {
        return value;
    }
}
