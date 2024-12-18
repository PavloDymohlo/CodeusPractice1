package org.shad.thursday.warmup;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Represents an HTTP Request with method, command, headers, body, and query string.
 */
public class Request {
    private final String method;
    private final String command;
    private final String body;
    private final String endpoint;
    private final List<HTTPHeader> headers;
    private final Map<String, String> queryString;

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
        if (method == null || method.isEmpty()) {
            throw new IllegalArgumentException("HTTP method cannot be null or empty");
        }
        if (command == null || command.isEmpty()) {
            throw new IllegalArgumentException("HTTP command cannot be null or empty");
        }

        this.method = method;
        this.command = command;
        this.body = body != null ? body : "";
        this.endpoint = method + " " + command;
        this.headers = headers != null ? Collections.unmodifiableList(headers) : Collections.emptyList();
        this.queryString = queryString != null ? Collections.unmodifiableMap(queryString) : Collections.emptyMap();
    }

    /**
     * Gets the HTTP method.
     *
     * @return HTTP method (e.g., GET, POST)
     */
    public String getMethod() {
        return method;
    }

    /**
     * Gets the HTTP command.
     *
     * @return HTTP command or path
     */
    public String getCommand() {
        return command;
    }

    /**
     * Gets the request body.
     *
     * @return Request body content
     */
    public String getBody() {
        return body;
    }

    /**
     * Gets the combined endpoint string ("METHOD path").
     *
     * @return Endpoint string
     */
    public String getEndpoint() {
        return endpoint;
    }

    /**
     * Gets the list of HTTP headers.
     *
     * @return Unmodifiable list of headers
     */
    public List<HTTPHeader> getHeaders() {
        return headers;
    }

    /**
     * Gets the query string parameters.
     *
     * @return Unmodifiable map of query parameters
     */
    public Map<String, String> getQueryString() {
        return queryString;
    }
}
