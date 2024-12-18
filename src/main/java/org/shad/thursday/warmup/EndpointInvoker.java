package org.shad.thursday.warmup;

import org.reflections.Reflections;
import org.shad.thursday.warmup.entity.Response;
import org.shad.thursday.warmup.entity.responseimpl.UnsuccessfulResponse;

import java.lang.reflect.Method;
import java.util.Optional;
import java.util.Set;

public class EndpointInvoker {

    private final Reflections reflections;

    /**
     * Constructor that initializes Reflections for a specific package.
     * Reflections instance is created once and reused to improve performance.
     */
    public EndpointInvoker(String basePackage) {
        this.reflections = new Reflections(basePackage);
    }

    /**
     * Handles the request by finding and invoking the appropriate endpoint.
     *
     * @param request The incoming HTTP request
     * @return A Response object (successful or unsuccessful)
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
     * Finds the method annotated with @EndpointHandler matching the request endpoint.
     *
     * @param endpoint The endpoint string (e.g., "GET /api/test")
     * @return Optional containing the matched method if found, empty otherwise
     */
    private Optional<Method> findMatchingMethod(String endpoint) {
        Set<Class<?>> controllers = reflections.getTypesAnnotatedWith(Controller.class);

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
     * Invokes the matched endpoint method with the given request.
     *
     * @param method  The method to invoke
     * @param request The request object to pass as a parameter
     * @return The response returned by the endpoint method
     * @throws Exception if reflection or method invocation fails
     */
    private Response invokeEndpointMethod(Method method, Request request) throws Exception {
        Object controllerInstance = method.getDeclaringClass().getDeclaredConstructor().newInstance();
        return (Response) method.invoke(controllerInstance, request);
    }
}
