package org.shad.thursday.main.entity;

/**
 * Represents a generic HTTP response. This interface defines the contract for
 * implementing classes to provide the necessary information and functionality
 * for an HTTP response.
 */
public interface Response {

    /**
     * Serializes the HTTP response into a string format.
     * The serialized format includes the status line, headers, and body,
     * formatted as a complete HTTP response.
     *
     * @return A string representation of the HTTP response.
     */
    String serialize();

    /**
     * Retrieves the HTTP status of the response.
     * The status typically includes the HTTP version, status code, and a reason phrase
     * (e.g., "HTTP/1.0 200 OK").
     *
     * @return The HTTP status line of the response.
     */
    String getStatus();

    /**
     * Retrieves the body of the HTTP response.
     * The body contains the payload of the response, which may be in JSON, HTML, or other formats,
     * depending on the implementing class.
     *
     * @return The body of the response as a string.
     */
    String getBody();
}