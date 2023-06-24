package controllers;

import java.util.EventListener;

/**
 * This is the custom interface made for the LoginEvent.
 */
public interface LoginListener extends EventListener {

        /**
         * This method is used for logIn button of LoginFrame.
         * @param e LoginEvent.
         */
        void loginEventOccurred(LoginEvent e);
}
