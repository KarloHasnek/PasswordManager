package controllers;

import models.User;
import java.util.EventObject;

/**
 * This class is used to create a custom event that will be called upon when a user logs in.
 */
public class LoginEvent extends EventObject {

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    private User user;

    /**
     * This is the constructor of the class.
     * @param source the object on which the Event initially occurred
     * @param user the user that logged in
     */
    public LoginEvent(Object source, User user) {
        super(source);
        this.user = user;
    }

    /**
     * This method returns the user that logged in.
     * @return the user that logged in
     */
    public User getUser() {
        return user;
    }

    /**
     * toString representation of the class.
     * @return the string representation of the class
     */
    @Override
    public String toString() {
        return "LoginEvent{" + "user=" + user + '}';
    }
}
