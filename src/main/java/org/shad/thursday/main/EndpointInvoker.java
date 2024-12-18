package org.shad.thursday.main;

import org.reflections.Reflections;
import org.shad.thursday.warmup.Request;
import org.shad.thursday.main.anotation.Controller;
import org.shad.thursday.main.anotation.EndpointHandler;
import org.shad.thursday.main.entity.Response;
import org.shad.thursday.main.entity.responseimpl.UnsuccessfulResponse;

import java.lang.reflect.Method;
import java.util.Optional;
import java.util.Set;

/**
 * The {@code EndpointInvoker} is responsible for dynamically discovering and invoking
 * endpoint methods annotated with {@link EndpointHandler} in classes annotated with {@link Controller}.
 * This class uses reflection and the Reflections library to locate and execute the appropriate
 * endpoint handler for an incoming {@link Request}.
 */
public class EndpointInvoker {

    //todo Implement field

    /**
     * Initializes the {@code EndpointInvoker} with a specific base package to scan for
     * annotated classes and methods.
     *
     * @param basePackage the base package to scan for {@link Controller} and {@link EndpointHandler} annotations.
     */
    public EndpointInvoker(String basePackage) {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }

    /**
     * Handles an incoming HTTP request by locating and invoking the appropriate endpoint handler.
     *
     * @param request the incoming {@link Request} object containing the endpoint and other HTTP details.
     * @return a {@link Response} object, representing the outcome of the request handling.
     *         - Returns a {@link UnsuccessfulResponse} with a 404 status if no matching endpoint is found.
     *         - Returns a {@link UnsuccessfulResponse} with a 500 status in case of any internal error.
     */
    public Response handleRequest(Request request) {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }

    /**
     * Searches for a method annotated with {@link EndpointHandler} that matches the provided endpoint.
     *
     * @param endpoint the endpoint string (e.g., "GET /api/test").
     * @return an {@link Optional} containing the matched {@link Method} if found, or an empty {@link Optional} otherwise.
     */
    private Optional<Method> findMatchingMethod(String endpoint) {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }

    /**
     * Invokes the specified endpoint method with the given {@link Request} object as a parameter.
     *
     * @param method  the {@link Method} to be invoked.
     * @param request the {@link Request} object to pass as a parameter to the method.
     * @return the {@link Response} returned by the invoked endpoint method.
     * @throws Exception if there is an error during method invocation,
     *                   such as instantiation failure or access issues.
     */
    private Response invokeEndpointMethod(Method method, Request request) throws Exception {
        // todo: Implement this method
        throw new UnsupportedOperationException();
    }
}