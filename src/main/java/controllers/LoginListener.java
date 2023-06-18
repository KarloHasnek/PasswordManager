package controllers;

import java.awt.event.ActionListener;

public interface LoginListener extends ActionListener {

        void loginEventOccurred(LoginEvent e);
}
