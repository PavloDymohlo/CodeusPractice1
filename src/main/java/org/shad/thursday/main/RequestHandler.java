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
        String method = null;
        String command = null;
        String body = "";
        int contentLength = 0; // To hold Content-Length value
        Map<String, String> queryString = new HashMap<>();
        List<HTTPHeader> headers = new ArrayList<>();

        String line;
        boolean hostHeaderPresent = false;

        while ((line = in.readLine()) != null && !line.isEmpty()) {
            System.out.println("Incoming line: " + line);

            if (method == null) {
                // Parse the request line
                String[] requestLineParts = line.split(" ");
                if (requestLineParts.length < 2) {
                    throw new IllegalArgumentException("Invalid request line: " + line);
                }
                method = parseMethod(line);
                command = parseCommand(line, queryString);
            } else {
                // Parse headers
                try {
                    HTTPHeader header = HTTPHeader.parse(line);
                    if (header.getName().equalsIgnoreCase("Content-Length")) {
                        contentLength = Integer.parseInt(header.getValue());
                        System.out.println("Content-Length parsed: " + contentLength);
                    } else {
                        headers.add(header); // Add other headers
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

        // Add Content-Length header explicitly (if present)
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
        return line.split(" ", 2)[0];
    }

    /**
     * Parses the command and query string from the request line.
     *
     * @param line the HTTP request line.
     * @param queryString the map to store parsed query string parameters.
     * @return the command (endpoint) from the request line.
     */
    private String parseCommand(String line, Map<String, String> queryString) {
        String[] parts = line.split(" ", 3)[1].split("\\?", 2);
        if (parts.length > 1) {
            parseQueryString(parts[1], queryString);
        }
        return parts[0];
    }

    /**
     * Parses and adds query string parameters to the provided map.
     *
     * @param query the query string portion of the URL.
     * @param queryString the map to store the parsed query string parameters.
     */
    private void parseQueryString(String query, Map<String, String> queryString) {
        for (String param : query.split("&")) {
            String[] keyValue = param.split("=", 2);
            if (keyValue.length == 2) {
                queryString.put(keyValue[0], keyValue[1]);
            } else {
                System.err.println("Skipping invalid query parameter: " + param);
            }
        }
    }

    /**
     * Parses and validates HTTP headers.
     *
     * @param headers the list to store valid headers.
     * @param line the raw header line to parse.
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
     * @throws NumberFormatException if the Content-Length value is invalid.
     */
    private int parseContentLength(String line) {
        try {
            return Integer.parseInt(line.split(":", 2)[1].trim());
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new NumberFormatException("Invalid Content-Length value: " + line);
        }
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
        char[] bodyChars = new char[contentLength];
        int read = in.read(bodyChars, 0, contentLength);
        if (read < contentLength) {
            throw new IOException("Unexpected end of stream when reading body.");
        }
        return new String(bodyChars);
    }
}