package org.shad.thursday.warmup;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Represents an HTTP Request with method, command, headers, body, and query string.
 */
public class Request {
    //todo Implement fields

    /**
     * Constructs an HTTP Request object.
     *
     * @param method      HTTP method (e.g., GET, POST)
     * @param command     The path/command of the request
     * @param body        The body content of the request
     * @param headers     List of HTTP headers
     * @param queryString Map of query string parameters
     * @throws IllegalArgumentException if method or command is null/empty
     */
    public Request(String method, String command, String body, List<HTTPHeader> headers, Map<String, String> queryString) {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }

    /**
     * Gets the HTTP method.
     *
     * @return HTTP method (e.g., GET, POST)
     */
    public String getMethod() {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }

    /**
     * Gets the HTTP command.
     *
     * @return HTTP command or path
     */
    public String getCommand() {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }

    /**
     * Gets the request body.
     *
     * @return Request body content
     */
    public String getBody() {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }

    /**
     * Gets the combined endpoint string ("METHOD path").
     *
     * @return Endpoint string
     */
    public String getEndpoint() {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }

    /**
     * Gets the list of HTTP headers.
     *
     * @return Unmodifiable list of headers
     */
    public List<HTTPHeader> getHeaders() {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }

    /**
     * Gets the query string parameters.
     *
     * @return Unmodifiable map of query parameters
     */
    public Map<String, String> getQueryString() {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }
}
