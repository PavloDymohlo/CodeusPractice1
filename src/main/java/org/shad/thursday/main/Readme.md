
**Main task - Our Server - Part 2 (45 min)**

**Steps to Complete the Task**

1. **Clone the Project**:
If you haven’t already cloned the repository, use the following link to get started:
[https://github.com/ShpakDmytro/CodeusPractice](https://github.com/ShpakDmytro/CodeusPractice)

2. **Navigate to the Classes and implement in next steps**:
Locate the following classes in the project:
• org.shad.thursday.main.RequestHandler - need implement first
• org.shad.thursday.main.EndpointInvoker - second
• org.shad.thursday.main.Server - last
3. **Implement the** RequestHandler **Class**:
• Parse incoming HTTP requests.
• Key Steps:
• **Method Parsing**: Implement parseMethod to extract the HTTP method (e.g., GET, POST).
• **Command Parsing**: Implement parseCommand to extract the endpoint and query parameters.
• **Header Parsing**: Validate and parse headers using the parseHeader method.
• **Body Parsing**: Read the request body based on the Content-Length header.
Example
```
POST /api/test HTTP/1.1  
Host: localhost  
Content-Length: 15  

{"key":"value"}
```

4. **Implement the** EndpointInvoker **Class**:

• Dynamically discover and invoke endpoint methods based on the incoming request.
• Key Steps:
• **Find Matching Methods**: Use the findMatchingMethod method to locate methods annotated with @EndpointHandler.
• **Method Invocation**: Implement invokeEndpointMethod to call the matched method with the parsed Request object.
• Handle 404 Not Found and 500 Internal Server Error responses gracefully.
Example:
• GET /test should trigger an annotated method and return a valid response.
• Invalid endpoints should return a 404 Not Found response.

5. **Implement the** Server **Class**:
• Follow the Javadoc provided in the Server class.
• Key Steps:
• **Initialize the Server**: Set up a fixed-thread pool using ExecutorService.
• **Implement** run: Accept incoming client connections and submit each connection to the thread pool.
• **Handle Client Connections**: Implement the handleConnection method to:
1. Parse the incoming HTTP request using RequestHandler.
2. Forward the parsed request to EndpointInvoker.
3. Send the response back to the client via the output stream.
Example Scenario:
• Handle both valid and invalid HTTP requests.
• Ensure proper exception handling and resource cleanup.