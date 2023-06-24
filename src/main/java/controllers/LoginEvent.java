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
    public LoginEvent(Object source, User user) {
        super(source);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "LoginEvent{" + "user=" + user + '}';
    }
}
