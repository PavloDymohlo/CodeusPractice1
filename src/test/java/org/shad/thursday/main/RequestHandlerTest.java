package org.shad.thursday.main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shad.thursday.warmup.HTTPHeader;
import org.shad.thursday.warmup.Request;

import java.io.BufferedReader;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class RequestHandlerTest {
    private RequestHandler requestHandler;

    @BeforeEach
    void setUp() {
        requestHandler = new RequestHandler();
    }

    @Test
    void testParseValidGetRequestWithQueryString() throws Exception {
        String rawRequest = "GET /test?param1=value1&param2=value2 HTTP/1.1\r\n" +
                "Host: localhost\r\n" +
                "Content-Length: 0\r\n" +
                "\r\n";

        BufferedReader reader = new BufferedReader(new StringReader(rawRequest));
        Request request = requestHandler.parseRequest(reader);

        System.out.println("Parsed headers: ");
        for (HTTPHeader header : request.getHeaders()) {
            System.out.println(header.getName() + " -> " + header.getValue());
        }

        assertEquals("GET", request.getMethod());
        assertEquals("/test", request.getCommand());
        assertEquals("", request.getBody());
        assertEquals(2, request.getQueryString().size());
        assertEquals("value1", request.getQueryString().get("param1"));
        assertEquals("value2", request.getQueryString().get("param2"));

        assertEquals(2, request.getHeaders().size(), "Header count mismatch");
        assertEquals("localhost", request.getHeaders().get(0).getValue());
        assertEquals("0", request.getHeaders().get(1).getValue());
    }

    @Test
    void testParseValidPostRequestWithBody() throws Exception {
        String rawRequest = "POST /submit HTTP/1.1\r\n" +
                "Host: localhost\r\n" +
                "Content-Length: 11\r\n" +
                "\r\n" +
                "Hello World";

        BufferedReader reader = new BufferedReader(new StringReader(rawRequest));
        Request request = requestHandler.parseRequest(reader);

        assertEquals("POST", request.getMethod());
        assertEquals("/submit", request.getCommand());
        assertEquals("Hello World", request.getBody());
        assertEquals(2, request.getHeaders().size());
        assertEquals("localhost", request.getHeaders().get(0).getValue());
    }

    @Test
    void testParseInvalidHeader() throws Exception {
        String rawRequest = "GET /test HTTP/1.1\r\n" +
                "InvalidHeaderWithoutColon\r\n" +
                "Host: localhost\r\n" +
                "\r\n";

        BufferedReader reader = new BufferedReader(new StringReader(rawRequest));
        Request request = requestHandler.parseRequest(reader);

        assertEquals("GET", request.getMethod());
        assertEquals("/test", request.getCommand());
        assertTrue(request.getBody().isEmpty());
        assertEquals(2, request.getHeaders().size()); // Only valid headers are parsed
        assertEquals("localhost", request.getHeaders().get(0).getValue());
    }

    @Test
    void testParseRequestWithMissingContentLength() throws Exception {
        String rawRequest = "POST /submit HTTP/1.1\r\n" +
                "Host: localhost\r\n" +
                "\r\n" +
                "UnexpectedBody";

        BufferedReader reader = new BufferedReader(new StringReader(rawRequest));
        Request request = requestHandler.parseRequest(reader);

        assertEquals("POST", request.getMethod());
        assertEquals("/submit", request.getCommand());
        assertEquals("", request.getBody()); // Body should be empty since Content-Length is missing
    }

    @Test
    void testParseMalformedRequestLine() {
        String rawRequest = "MALFORMED_REQUEST_LINE\r\n" +
                "Host: localhost\r\n" +
                "\r\n";

        BufferedReader reader = new BufferedReader(new StringReader(rawRequest));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            requestHandler.parseRequest(reader);
        });

        assertTrue(exception.getMessage().contains("Invalid request line"));
    }

    @Test
    void testParseRequestWithZeroContentLength() throws Exception {
        String rawRequest = "POST /submit HTTP/1.1\r\n" +
                "Host: localhost\r\n" +
                "Content-Length: 0\r\n" +
                "\r\n";

        BufferedReader reader = new BufferedReader(new StringReader(rawRequest));
        Request request = requestHandler.parseRequest(reader);

        assertEquals("POST", request.getMethod());
        assertEquals("/submit", request.getCommand());
        assertEquals("", request.getBody()); // No body
        assertEquals(2, request.getHeaders().size());
        assertEquals("localhost", request.getHeaders().get(0).getValue());
    }

    @Test
    void testParseRequestWithInvalidContentLength() throws Exception {
        String rawRequest = "POST /test HTTP/1.1\r\n" +
                "Host: localhost\r\n" +
                "Content-Length: invalid\r\n" +
                "\r\n";

        BufferedReader reader = new BufferedReader(new StringReader(rawRequest));
        Request request = requestHandler.parseRequest(reader);

        assertEquals("POST", request.getMethod());
        assertEquals("/test", request.getCommand());
        assertEquals("", request.getBody()); // Body should be empty
        assertEquals(2, request.getHeaders().size()); // Only 'Host' header parsed
        assertEquals("localhost", request.getHeaders().get(0).getValue());
    }
}