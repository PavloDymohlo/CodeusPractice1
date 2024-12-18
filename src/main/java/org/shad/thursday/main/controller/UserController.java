package org.shad.thursday.main.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.shad.thursday.main.entity.User;
import org.shad.thursday.main.entity.UserDTO;
import org.shad.thursday.main.service.UserService;
import org.shad.thursday.main.anotation.Controller;
import org.shad.thursday.main.anotation.EndpointHandler;
import org.shad.thursday.warmup.Request;
import org.shad.thursday.main.entity.Response;
import org.shad.thursday.main.entity.responseimpl.SuccessfulResponse;
import org.shad.thursday.main.entity.responseimpl.UnsuccessfulResponse;

/**
 * Controller for managing user-related operations.
 * Handles requests related to user creation and other user actions.
 */
@Controller
public class UserController {

    private final UserService userService = new UserService();
    private static final ObjectMapper MAPPER = new ObjectMapper(); // Reusable ObjectMapper for JSON processing

    /**
     * Default constructor for {@link UserController}.
     */
    public UserController() {}

    /**
     * Handles user sign-up requests. This method processes HTTP POST requests
     * to the `/user` endpoint, deserializes the request body into a {@link UserDTO},
     * adds the user using {@link UserService}, and returns an appropriate response.
     *
     * @param objRequest The HTTP request object containing method, headers, body, and other data.
     * @return A {@link SuccessfulResponse} if the user is successfully created, or
     *         an {@link UnsuccessfulResponse} for invalid input or server errors.
     */
    @EndpointHandler(endpoint = "POST /user")
    public Response cmdSignUp(Request objRequest) {
        try {
            UserDTO userDTO = MAPPER.readValue(objRequest.getBody(), UserDTO.class);
            User user = userService.addUser(userDTO);

            return new SuccessfulResponse("201 Created", MAPPER.writeValueAsString(user));
        } catch (IllegalArgumentException e) {
            return new UnsuccessfulResponse("400 Bad Request", e.getMessage());
        } catch (Exception e) {
            return new UnsuccessfulResponse("500 Internal Server Error", "Error processing request");
        }
    }
}