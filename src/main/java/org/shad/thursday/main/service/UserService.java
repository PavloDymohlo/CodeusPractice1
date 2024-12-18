package org.shad.thursday.main.service;

import org.shad.thursday.main.entity.User;
import org.shad.thursday.main.entity.UserDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * A service class for managing user-related operations, such as adding users.
 */
public class UserService {

    private final List<User> users = new ArrayList<>();

    /**
     * Adds a new user to the system.
     *
     * @param userDTO The {@link UserDTO} containing the user's details.
     * @return The created {@link User} object with a unique ID.
     * @throws IllegalArgumentException If the first name or last name in {@code userDTO} is null or empty.
     */
    public User addUser(UserDTO userDTO) {
        if (userDTO.getFirstName() == null || userDTO.getFirstName().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be null or empty");
        }
        if (userDTO.getLastName() == null || userDTO.getLastName().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be null or empty");
        }

        User user = new User(UUID.randomUUID(), userDTO.getFirstName(), userDTO.getLastName());
        users.add(user);
        return user;
    }
}