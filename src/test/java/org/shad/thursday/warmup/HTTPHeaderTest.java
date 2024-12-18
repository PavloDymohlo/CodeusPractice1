package org.shad.thursday.warmup;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class HTTPHeaderTest {

    @Test
    public void testCreateHeader() {
        HTTPHeader header = HTTPHeader.of("Content-Type", "application/json");
        assertEquals("content-type", header.getName(), "Header name should be normalized to lowercase");
        assertEquals("application/json", header.getValue(), "Header value should match the provided value");
    }


    @Test
    public void testParseHeader() {
        HTTPHeader header = HTTPHeader.parse("Content-Length: 1234");
        assertEquals("content-length", header.getName(), "Parsed name should be normalized to lowercase");
        assertEquals("1234", header.getValue(), "Parsed value should match the raw header");
    }


    @Test
    public void testParseHeaderWithWhitespace() {
        HTTPHeader header = HTTPHeader.parse("  Accept-Encoding : gzip, deflate ");
        assertEquals("accept-encoding", header.getName(), "Parsed name should be trimmed and normalized");
        assertEquals("gzip, deflate", header.getValue(), "Parsed value should be trimmed");
    }


    @Test
    public void testParseNullHeader() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            HTTPHeader.parse(null);
        });
        assertEquals("Invalid header format: Header cannot be null or blank", exception.getMessage(), "Should throw for null header");
    }

    @Test
    public void testParseInvalidHeaderFormat() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            HTTPHeader.parse("InvalidHeader");
        });
        assertEquals("Invalid header format: InvalidHeader", exception.getMessage(), "Should throw for invalid format");
    }

    @Test
    public void testParseEmptyName() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            HTTPHeader.parse(": value");
        });
        assertEquals("Header name cannot be null or empty", exception.getMessage(), "Should throw for empty name");
    }

    @Test
    public void testCreateHeaderNullName() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            HTTPHeader.of(null, "value");
        });
        assertEquals("Header name cannot be null or empty", exception.getMessage(), "Should throw for null name");
    }

    @Test
    public void testCreateHeaderEmptyName() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            HTTPHeader.of("", "value");
        });
        assertEquals("Header name cannot be null or empty", exception.getMessage(), "Should throw for empty name");
    }

    @Test
    public void testCreateHeaderNullValue() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            HTTPHeader.of("Name", null);
        });
        assertEquals("Header value cannot be null", exception.getMessage(), "Should throw for null value");
    }

    @ParameterizedTest
    @ValueSource(strings = { "InvalidHeader" })
    void testParseInvalidHeaders(String rawHeader) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            HTTPHeader.parse(rawHeader);
        });

        // Allow either the specific message or a general invalid format message
        String actualMessage = exception.getMessage();
        assertTrue(
                actualMessage.contains("Missing ':' separator in header") ||
                        actualMessage.contains("Invalid header format"),
                "Expected message to indicate invalid header format or missing ':'"
        );
    }
    @Test
    void testParseValidHeaderWithWhitespace() {
        HTTPHeader header = HTTPHeader.parse("   Accept-Encoding : gzip, deflate ");
        assertEquals("accept-encoding", header.getName());
        assertEquals("gzip, deflate", header.getValue());
    }

}