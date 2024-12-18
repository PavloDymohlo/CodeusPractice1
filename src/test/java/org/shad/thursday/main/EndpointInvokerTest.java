package org.shad.thursday.main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.shad.thursday.main.anotation.Controller;
import org.shad.thursday.main.anotation.EndpointHandler;
import org.shad.thursday.main.entity.Response;
import org.shad.thursday.main.entity.responseimpl.UnsuccessfulResponse;
import org.shad.thursday.main.entity.responseimpl.SuccessfulResponse;
import org.shad.thursday.warmup.Request;

import static org.junit.jupiter.api.Assertions.*;

class EndpointInvokerTest {
    private EndpointInvoker invoker;

    @BeforeEach
    public void setup() {
        invoker = new EndpointInvoker("org.shad.thursday");
    }

    @Test
    public void testHandleRequest_SuccessfulEndpoint() {
        Request request = new Request("GET", "/test", null, null, null);

        Response response = invoker.handleRequest(request);

        assertInstanceOf(SuccessfulResponse.class, response, "Response should be of type SuccessfulResponse");
        assertEquals("HTTP/1.0 200 OK", response.getStatus());
        assertEquals("{\"message\":\"Test endpoint hit successfully\"}", response.getBody());
    }

    @Test
    public void testHandleRequest_SubmitEndpoint() {
        Request request = new Request("POST", "/submit", "Sample Body", null, null);

        Response response = invoker.handleRequest(request);

        assertInstanceOf(SuccessfulResponse.class, response);
        assertEquals("HTTP/1.0 200 OK", response.getStatus());
        assertEquals("{\"message\":\"Submit endpoint hit with body: Sample Body\"}", response.getBody());
    }

    @Test
    public void testHandleRequest_UnknownEndpoint() {
        Request request = new Request("GET", "/unknown", null, null, null);

        Response response = invoker.handleRequest(request);

        assertInstanceOf(UnsuccessfulResponse.class, response, "Response should be of type UnsuccessfulResponse");
        assertEquals("HTTP/1.0 404 Not Found", response.getStatus());
        assertEquals("{\"error\":\"Unknown command\"}", response.getBody());
    }

    @Test
    public void testHandleRequest_ReflectionError() {
        Request request = new Request("GET", "/error", null, null, null);

        Response response = invoker.handleRequest(request);

        assertInstanceOf(UnsuccessfulResponse.class, response);
        assertEquals("HTTP/1.0 500 Internal Server Error", response.getStatus());
        assertEquals("{\"error\":\"Server error\"}", response.getBody());
    }

    @Test
    public void testHandleRequest_EmptyPackage() {
        EndpointInvoker emptyInvoker = new EndpointInvoker("org.shad.empty");
        Request request = new Request("GET", "/test", null, null, null);

        Response response = emptyInvoker.handleRequest(request);

        assertInstanceOf(UnsuccessfulResponse.class, response);
        assertEquals("HTTP/1.0 404 Not Found", response.getStatus());
        assertEquals("{\"error\":\"Unknown command\"}", response.getBody());
    }

    @Controller
    public static class TestController {

        @EndpointHandler(endpoint = "GET /test")
        public Response handleTest(Request request) {
            return new SuccessfulResponse("200 OK", "Test endpoint hit successfully");
        }

        @EndpointHandler(endpoint = "POST /submit")
        public Response handleSubmit(Request request) {
            return new SuccessfulResponse("200 OK", "Submit endpoint hit with body: " + request.getBody());
        }
    }

    @Controller
    public static class ErrorController {

        @EndpointHandler(endpoint = "GET /error")
        public Response handleError(Request request) {
            throw new RuntimeException("Simulated reflection error");
        }
    }
}