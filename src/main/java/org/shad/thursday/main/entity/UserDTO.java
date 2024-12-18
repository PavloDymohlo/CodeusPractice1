package org.shad.thursday.main.entity;

/**
 * A Data Transfer Object (DTO) for user details.
 * Used to encapsulate the user's first name and last name.
 */
public class UserDTO {
    private String firstName;
    private String lastName;

    /**
     * Default constructor for creating an empty {@link UserDTO}.
     */
    public UserDTO() {}

    /**
     * Constructor to initialize a {@link UserDTO} with the given first name and last name.
     *
     * @param firstName The user's first name.
     * @param lastName  The user's last name.
     */
    public UserDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Gets the user's first name.
     *
     * @return The first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the user's first name.
     *
     * @param firstName The first name to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the user's last name.
     *
     * @return The last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the user's last name.
     *
     * @param lastName The last name to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}