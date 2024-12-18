package org.shad.thursday.main;

import org.shad.thursday.warmup.HTTPHeader;
import org.shad.thursday.warmup.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Handles parsing of HTTP requests from an input stream.
 */
public class RequestHandler {

    /**
     * Parses the incoming HTTP request from a {@link BufferedReader}.
     *
     * @param in the BufferedReader containing the incoming HTTP request.
     * @return a {@link Request} object representing the parsed HTTP request.
     * @throws IOException if there is an error reading from the input stream.
     * @throws IllegalArgumentException if the request is invalid or missing required headers.
     */
    public Request parseRequest(BufferedReader in) throws IOException {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }

    /**
     * Parses the HTTP method from the request line.
     *
     * @param line the HTTP request line.
     * @return the HTTP method as a string (e.g., GET, POST).
     */
    private String parseMethod(String line) {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }

    /**
     * Parses the command and query string from the request line.
     *
     * @param line the HTTP request line.
     * @param queryString the map to store parsed query string parameters.
     * @return the command (endpoint) from the request line.
     */
    private String parseCommand(String line, Map<String, String> queryString) {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }

    /**
     * Parses and adds query string parameters to the provided map.
     *
     * @param query the query string portion of the URL.
     * @param queryString the map to store the parsed query string parameters.
     */
    private void parseQueryString(String query, Map<String, String> queryString) {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }

    /**
     * Parses and validates HTTP headers.
     *
     * @param headers the list to store valid headers.
     * @param line the raw header line to parse.
     */
    private void parseHeader(List<HTTPHeader> headers, String line) {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }

    /**
     * Parses the Content-Length header value.
     *
     * @param line the raw Content-Length header line.
     * @return the parsed Content-Length value.
     * @throws NumberFormatException if the Content-Length value is invalid.
     */
    private int parseContentLength(String line) {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }

    /**
     * Reads the request body based on the Content-Length value.
     *
     * @param in the BufferedReader containing the HTTP request.
     * @param contentLength the expected length of the request body.
     * @return the parsed request body as a string.
     * @throws IOException if the body could not be read completely.
     */
    private String readBody(BufferedReader in, int contentLength) throws IOException {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }
}