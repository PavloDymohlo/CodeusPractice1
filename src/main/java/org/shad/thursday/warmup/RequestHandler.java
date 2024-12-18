package org.shad.thursday.warmup;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestHandler {

    /**
     * Parses the incoming HTTP request from the BufferedReader.
     *
     * @param in BufferedReader with the incoming HTTP request.
     * @return Parsed Request object.
     * @throws IOException if there is an error reading from the stream.
     */
    public Request parseRequest(BufferedReader in) throws IOException {
        String method = null;
        String command = null;
        String body = "";
        int contentLength = 0;
        Map<String, String> queryString = new HashMap<>();
        List<HTTPHeader> headers = new ArrayList<>();

        String line;

        // Parse the request line and headers
        while ((line = in.readLine()) != null && !line.isEmpty()) {
            System.out.println("Incoming line: " + line);

            if (method == null) {
                method = parseMethod(line);
                command = parseCommand(line, queryString);
            } else if (line.startsWith("Content-Length:")) {
                contentLength = parseContentLength(line);
            } else {
                parseHeader(headers, line);
            }
        }

        // Read the body if Content-Length is provided
        if (contentLength > 0) {
            body = readBody(in, contentLength);
        }

        return new Request(method, command, body, headers, queryString);
    }

    /**
     * Parses the HTTP method from the request line.
     */
    private String parseMethod(String line) {
        return line.split(" ", 2)[0];
    }

    /**
     * Parses the command and query string from the request line.
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
     */
    private void parseHeader(List<HTTPHeader> headers, String line) {
        try {
            headers.add(HTTPHeader.parse(line));
        } catch (IllegalArgumentException e) {
            System.err.println("Skipping invalid header: " + line);
        }
    }

    /**
     * Parses the Content-Length header value.
     */
    private int parseContentLength(String line) {
        try {
            return Integer.parseInt(line.split(":", 2)[1].trim());
        } catch (NumberFormatException e) {
            System.err.println("Invalid Content-Length value: " + line);
            return 0;
        }
    }

    /**
     * Reads the request body based on the Content-Length value.
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
