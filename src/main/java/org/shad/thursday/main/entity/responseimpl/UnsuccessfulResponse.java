package org.shad.thursday.main.entity.responseimpl;

import org.shad.thursday.main.entity.Response;

/**
 * Represents an unsuccessful HTTP response with an error message in JSON format.
 * Implements the {@link Response} interface.
 */
public class UnsuccessfulResponse implements Response {

    private final String status;
    private final String bodyResponse;

    /**
     * Constructs an {@code UnsuccessfulResponse} with the given status and error message.
     * The response body is formatted as a JSON object with an "error" key.
     *
     * @param status       The HTTP status code and reason phrase (e.g., "404 Not Found").
     * @param bodyResponse The error message to include in the JSON response body.
     */
    public UnsuccessfulResponse(String status, String bodyResponse) {
        this.status = "HTTP/1.0 " + status;
        this.bodyResponse = "{\"error\":\"" + bodyResponse + "\"}";
    }

    /**
     * Serializes the HTTP response into a string format, including the status line,
     * headers, and body. The response body is included as a JSON string with an "error" key.
     *
     * @return The serialized HTTP response.
     */
    @Override
    public String serialize() {
        return this.status + "\r\n" +
                "Content-length: " + this.bodyResponse.length() + "\r\n" +
                "Content-type: application/json" + "\r\n" +
                "\r\n" +
                this.bodyResponse;
    }

    /**
     * Retrieves the HTTP status line of the response.
     *
     * @return The HTTP status line.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Retrieves the JSON-formatted response body containing the error message.
     *
     * @return The response body as a JSON string.
     */
    public String getBody() {
        return bodyResponse;
    }
}