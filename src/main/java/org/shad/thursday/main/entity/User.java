package org.shad.thursday.main.entity;

import java.util.UUID;

/**
 * Represents a user entity with a unique identifier, first name, and last name.
 */
public class User {
    private final UUID id;
    private final String firstName;
    private final String lastName;

    /**
     * Constructs a new {@code User} instance.
     *
     * @param id        the unique identifier for the user
     * @param firstName the first name of the user
     * @param lastName  the last name of the user
     */
    public User(UUID id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Gets the unique identifier of the user.
     *
     * @return the user's unique identifier
     */
    public UUID getId() {
        return id;
    }

    /**
     * Gets the first name of the user.
     *
     * @return the user's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the last name of the user.
     *
     * @return the user's last name
     */
    public String getLastName() {
        return lastName;
    }
}