package org.shad.thursday.main.entity.responseimpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.shad.thursday.main.entity.Response;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Represents a successful HTTP response with a JSON body.
 * Implements the {@link Response} interface.
 */
public class SuccessfulResponse implements Response {

    private String status;
    private String bodyResponse;

    /**
     * Constructs a {@code SuccessfulResponse} with the given status and message.
     * Automatically formats the response body as a JSON object.
     *
     * @param status       The HTTP status code and reason phrase (e.g., "200 OK").
     * @param bodyResponse A message to include in the JSON response body.
     * @throws RuntimeException if the response body cannot be serialized to JSON.
     */
    public SuccessfulResponse(String status, String bodyResponse) {
        ObjectMapper mapper = new ObjectMapper();
        this.status = "HTTP/1.0 " + status;
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", bodyResponse);

        try {
            this.bodyResponse = mapper.writeValueAsString(responseBody);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize response body", e);
        }
    }

    /**
     * Serializes the HTTP response into a string format, including status line,
     * headers, and body. The response body is included as a JSON string.
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
     * Sets the HTTP status line of the response.
     *
     * @param status The new HTTP status line.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Retrieves the JSON-formatted response body.
     *
     * @return The response body as a JSON string.
     */
    public String getBody() {
        return bodyResponse;
    }

    /**
     * Sets the JSON-formatted response body.
     *
     * @param bodyResponse The new response body as a JSON string.
     */
    public void setBodyResponse(String bodyResponse) {
        this.bodyResponse = bodyResponse;
    }
}