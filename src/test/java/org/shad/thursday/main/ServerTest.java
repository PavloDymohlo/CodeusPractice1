package org.shad.thursday.main;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.*;
import java.lang.reflect.Method;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class ServerTest {

    @Test
    void testHandleConnection() throws Exception {

        String expectedResponse =
                "HTTP/1.0 200 OK\r\n" +
                        "Content-length: 44\r\n" +
                        "Content-type: application/json\r\n" +
                        "\r\n" +
                        "{\"message\":\"Test endpoint hit successfully\"}";

        Socket mockSocket = Mockito.mock(Socket.class);
        InputStream mockInputStream = new ByteArrayInputStream(
                ("GET /test HTTP/1.1\r\n" +
                        "Host: localhost\r\n\r\n").getBytes()
        );
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        when(mockSocket.getInputStream()).thenReturn(mockInputStream);
        when(mockSocket.getOutputStream()).thenReturn(outputStream);

        Server server = new Server();
        Method handleConnection = Server.class.getDeclaredMethod("handleConnection", Socket.class);
        handleConnection.setAccessible(true);
        handleConnection.invoke(server, mockSocket);

        String actualResponse = outputStream.toString();
        String[] responseLines = actualResponse.split("\r\n");

        assertEquals("HTTP/1.0 200 OK", responseLines[0], "Status line mismatch");
        assertTrue(responseLines[1].contains("Content-length: 44"), "Content-length header mismatch");
        assertTrue(responseLines[2].contains("Content-type: application/json"), "Content-type header mismatch");

        String responseBody = responseLines[4];
        assertEquals("{\"message\":\"Test endpoint hit successfully\"}", responseBody, "Response body mismatch");
    }

    @Test
    void testHandleConnectionWithMalformedRequest() throws Exception {
        String expectedResponse = "HTTP/1.0 400 Bad Request";

        Socket mockSocket = Mockito.mock(Socket.class);
        InputStream mockInputStream = new ByteArrayInputStream(
                ("MALFORMED_REQUEST_LINE\r\nHost: localhost\r\n\r\n").getBytes()
        );
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        when(mockSocket.getInputStream()).thenReturn(mockInputStream);
        when(mockSocket.getOutputStream()).thenReturn(outputStream);

        Server server = new Server();
        Method handleConnection = Server.class.getDeclaredMethod("handleConnection", Socket.class);
        handleConnection.setAccessible(true);
        handleConnection.invoke(server, mockSocket);

        String actualResponse = outputStream.toString();
        assertTrue(actualResponse.contains(expectedResponse), "Response for malformed request is incorrect");
    }

    @Test
    void testHandleConnectionWithUnsupportedMethod() throws Exception {
        String expectedResponse = "{\"error\":\"Unknown command\"}";

        Socket mockSocket = Mockito.mock(Socket.class);
        InputStream mockInputStream = new ByteArrayInputStream(
                ("PUT /test HTTP/1.1\r\nHost: localhost\r\n\r\n").getBytes()
        );
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        when(mockSocket.getInputStream()).thenReturn(mockInputStream);
        when(mockSocket.getOutputStream()).thenReturn(outputStream);

        Server server = new Server();
        Method handleConnection = Server.class.getDeclaredMethod("handleConnection", Socket.class);
        handleConnection.setAccessible(true);
        handleConnection.invoke(server, mockSocket);

        String actualResponse = outputStream.toString();
        assertTrue(actualResponse.contains(expectedResponse), "Response for unsupported method is incorrect");
    }

    @Test
    void testHandleConnectionWithMissingHostHeader() throws Exception {
        String expectedResponse = "HTTP/1.0 400 Bad Request";

        Socket mockSocket = Mockito.mock(Socket.class);
        InputStream mockInputStream = new ByteArrayInputStream(
                ("GET /test HTTP/1.1\r\n\r\n").getBytes() // Missing Host header
        );
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        when(mockSocket.getInputStream()).thenReturn(mockInputStream);
        when(mockSocket.getOutputStream()).thenReturn(outputStream);

        Server server = new Server();
        Method handleConnection = Server.class.getDeclaredMethod("handleConnection", Socket.class);
        handleConnection.setAccessible(true);
        handleConnection.invoke(server, mockSocket);

        String actualResponse = outputStream.toString();
        assertTrue(actualResponse.contains(expectedResponse), "Response for missing Host header is incorrect");
    }

}