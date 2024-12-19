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
     * @throws IOException              if there is an error reading from the input stream.
     * @throws IllegalArgumentException if the request is invalid or missing required headers.
     */
    public Request parseRequest(BufferedReader in) throws IOException {
        String method = null;
        String command = null;
        String body = "";
        int contentLength = 0;
        Map<String, String> queryString = new HashMap<>();
        List<HTTPHeader> headers = new ArrayList<>();

        String line;
        boolean hostHeaderPresent = false;

        while ((line = in.readLine()) != null && !line.isEmpty()) {
            System.out.println("Incoming line: " + line);

            if (method == null) {
                String[] requestLineParts = line.split(" ");
                if (requestLineParts.length < 2) {
                    throw new IllegalArgumentException("Invalid request line: " + line);
                }
                method = parseMethod(line);
                command = parseCommand(line, queryString);
            } else {
                try {
                    HTTPHeader header = HTTPHeader.parse(line);
                    if (header.getName().equalsIgnoreCase("Content-Length")) {
                        contentLength = Integer.parseInt(header.getValue());
                        System.out.println("Content-Length parsed: " + contentLength);
                    } else {
                        headers.add(header);
                    }
                    if (header.getName().equalsIgnoreCase("Host")) {
                        hostHeaderPresent = true;
                    }
                } catch (IllegalArgumentException e) {
                    System.err.println("Skipping invalid header: " + line);
                }
            }
        }
        if (!hostHeaderPresent) {
            throw new IllegalArgumentException("400 Bad Request: Missing Host header");
        }
        if (contentLength > 0) {
            body = readBody(in, contentLength);
        }
        if (contentLength >= 0) {
            headers.add(HTTPHeader.of("Content-Length", String.valueOf(contentLength)));
        }
        return new Request(method, command, body, headers, queryString);
    }

    /**
     * Parses the HTTP method from the request line.
     *
     * @param line the HTTP request line.
     * @return the HTTP method as a string (e.g., GET, POST).
     */
    private String parseMethod(String line) {
        String[] parts = line.split(" ");
        if (parts.length < 0) {
            throw new IllegalArgumentException("Invalid request line: missing method");
        }
        return parts[0].toUpperCase();
    }

    /**
     * Parses the command and query string from the request line.
     *
     * @param line        the HTTP request line.
     * @param queryString the map to store parsed query string parameters.
     * @return the command (endpoint) from the request line.
     */
    private String parseCommand(String line, Map<String, String> queryString) {
        String[] parts = line.split(" ");
        if (parts.length < 2) {
            throw new IllegalArgumentException("Invalid request line: missing command");
        }
        String url = parts[1];
        String[] urlParts = url.split("\\?", 2);
        if (urlParts.length > 1) {
            parseQueryString(urlParts[1], queryString);
        }
        return urlParts[0];
    }

    /**
     * Parses and adds query string parameters to the provided map.
     *
     * @param query       the query string portion of the URL.
     * @param queryString the map to store the parsed query string parameters.
     */
    private void parseQueryString(String query, Map<String, String> queryString) {
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=", 2);
            String key = keyValue[0].trim();
            String value = keyValue.length > 1 ? keyValue[1].trim() : "";
            queryString.put(key, value);
        }
    }

    /**
     * Parses and validates HTTP headers.
     *
     * @param headers the list to store valid headers.
     * @param line    the raw header line to parse.
     */
    private void parseHeader(List<HTTPHeader> headers, String line) {
        try {
            System.out.println("Attempting to parse header: " + line);
            HTTPHeader header = HTTPHeader.parse(line);
            headers.add(header);
            System.out.println("Parsed header: " + header.getName() + " -> " + header.getValue());
        } catch (IllegalArgumentException e) {
            System.err.println("Skipping invalid header: " + line + " - Error: " + e.getMessage());
        }
    }

    /**
     * Parses the Content-Length header value.
     *
     * @param line the raw Content-Length header line.
     * @return the parsed Content-Length value.
     * @throwspa NumberFormatException if the Content-Length value is invalid.
     */
    private int parseContentLength(String line) {
        try {
            return Integer.parseInt(line.split(":")[1].trim());
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Invalid Content-Length value: " + line, e);
        }
    }

    /**
     * Reads the request body based on the Content-Length value.
     *
     * @param in            the BufferedReader containing the HTTP request.
     * @param contentLength the expected length of the request body.
     * @return the parsed request body as a string.
     * @throws IOException if the body could not be read completely.
     */
    private String readBody(BufferedReader in, int contentLength) throws IOException {
        char[] buffer = new char[contentLength];
        int bytesRead = in.read(buffer, 0, contentLength);
        if (bytesRead < contentLength) {
            throw new IOException("Request body length does not match Content-Length");
        }
        return new String(buffer);
    }
}