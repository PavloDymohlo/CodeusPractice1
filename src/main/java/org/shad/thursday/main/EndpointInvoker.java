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

    private final Reflections reflections;

    /**
     * Initializes the {@code EndpointInvoker} with a specific base package to scan for
     * annotated classes and methods.
     *
     * @param basePackage the base package to scan for {@link Controller} and {@link EndpointHandler} annotations.
     */
    public EndpointInvoker(String basePackage) {
        this.reflections = new Reflections(basePackage);
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
        try {
            Optional<Method> matchedMethod = findMatchingMethod(request.getEndpoint());
            if (matchedMethod.isEmpty()) {
                return new UnsuccessfulResponse("404 Not Found", "Unknown command");
            }
            return invokeEndpointMethod(matchedMethod.get(), request);
        } catch (Exception e) {
            e.printStackTrace();
            return new UnsuccessfulResponse("500 Internal Server Error", "Server error");
        }
    }

    /**
     * Searches for a method annotated with {@link EndpointHandler} that matches the provided endpoint.
     *
     * @param endpoint the endpoint string (e.g., "GET /api/test").
     * @return an {@link Optional} containing the matched {@link Method} if found, or an empty {@link Optional} otherwise.
     */
    private Optional<Method> findMatchingMethod(String endpoint) {
        // Retrieve all classes annotated with @Controller in the specified package
        Set<Class<?>> controllers = reflections.getTypesAnnotatedWith(Controller.class);

        // Iterate through all classes and their methods to find a match
        for (Class<?> controllerClass : controllers) {
            for (Method method : controllerClass.getDeclaredMethods()) {
                EndpointHandler annotation = method.getAnnotation(EndpointHandler.class);
                if (annotation != null && annotation.endpoint().equals(endpoint)) {
                    return Optional.of(method);
                }
            }
        }
        return Optional.empty();
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
        // Create an instance of the controller class containing the method
        Object controllerInstance = method.getDeclaringClass().getDeclaredConstructor().newInstance();

        // Invoke the method with the Request object and return the response
        return (Response) method.invoke(controllerInstance, request);
    }
}