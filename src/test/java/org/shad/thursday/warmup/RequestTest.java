package org.shad.thursday.warmup;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RequestTest {
    @Test
    public void testValidRequestCreation() {
        List<HTTPHeader> headers = List.of(HTTPHeader.of("Content-Type", "application/json"));
        Map<String, String> queryString = Map.of("name", "John");

        Request request = new Request("GET", "/api/test", "Body Content", headers, queryString);

        assertEquals("GET", request.getMethod(), "Method should be GET");
        assertEquals("/api/test", request.getCommand(), "Command should be /api/test");
        assertEquals("Body Content", request.getBody(), "Body should match");
        assertEquals("GET /api/test", request.getEndpoint(), "Endpoint should combine method and command");
        assertEquals(headers, request.getHeaders(), "Headers should match");
        assertEquals(queryString, request.getQueryString(), "Query string should match");
    }

    @Test
    public void testNullFields() {
        Request request = new Request("POST", "/api/null", null, null, null);

        assertEquals("POST", request.getMethod(), "Method should be POST");
        assertEquals("/api/null", request.getCommand(), "Command should be /api/null");
        assertEquals("", request.getBody(), "Body should default to empty string");
        assertTrue(request.getHeaders().isEmpty(), "Headers should be empty");
        assertTrue(request.getQueryString().isEmpty(), "Query string should be empty");
    }

   @Test
    public void testNullMethod() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Request(null, "/api/error", "Body", null, null);
        });
        assertEquals("HTTP method cannot be null or empty", exception.getMessage());
    }

    @Test
    public void testEmptyMethod() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Request("", "/api/error", "Body", null, null);
        });
        assertEquals("HTTP method cannot be null or empty", exception.getMessage());
    }

    @Test
    public void testNullCommand() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Request("GET", null, "Body", null, null);
        });
        assertEquals("HTTP command cannot be null or empty", exception.getMessage());
    }
    @Test
    public void testEmptyCommand() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Request("GET", "", "Body", null, null);
        });
        assertEquals("HTTP command cannot be null or empty", exception.getMessage());
    }

    @Test
    public void testImmutability() {
        List<HTTPHeader> headers = List.of(HTTPHeader.of("Authorization", "Bearer token"));
        Map<String, String> queryString = Map.of("id", "123");

        Request request = new Request("PUT", "/api/immutable", "Immutable Body", headers, queryString);

        assertThrows(UnsupportedOperationException.class, () -> request.getHeaders().add(HTTPHeader.of("New-Header", "Value")));
        assertThrows(UnsupportedOperationException.class, () -> request.getQueryString().put("newKey", "newValue"));
    }

    @Test
    public void testEmptyHeadersAndQueryString() {
        Request request = new Request("GET", "/api/empty", "Empty Test", List.of(), Map.of());

        assertEquals("GET", request.getMethod());
        assertEquals("/api/empty", request.getCommand());
        assertEquals("Empty Test", request.getBody());
        assertTrue(request.getHeaders().isEmpty(), "Headers should be empty");
        assertTrue(request.getQueryString().isEmpty(), "Query string should be empty");
    }

    @Test
    public void testSpecialCharacters() {
        List<HTTPHeader> headers = List.of(HTTPHeader.of("X-Special", "!@#$%^&*()"));
        Map<String, String> queryString = Map.of("key", "value&another");

        Request request = new Request("GET", "/api/special?param=value", "Body!@#$%^&*", headers, queryString);

        assertEquals("GET", request.getMethod());
        assertEquals("/api/special?param=value", request.getCommand());
        assertEquals("Body!@#$%^&*", request.getBody());
        assertEquals(headers, request.getHeaders());
        assertEquals(queryString, request.getQueryString());
    }

}