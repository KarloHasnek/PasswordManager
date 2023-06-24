package controllers;

import models.DBHandler;
import models.User;
import view.MainFrame;
import view.PasswordDialogFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * This class is responsible for handling the event that occurs between MainFrame and PasswordDialogFrame.
 */
public class MainActionListener implements ActionListener {

    private MainFrame mainFrame;
    private PasswordDialogFrame passwordDialogFrame;

    /**
     * Method handling the event.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == passwordDialogFrame.getAddButton()) {
            String website = passwordDialogFrame.getWebsiteField().getText();
            String username = passwordDialogFrame.getUsernameField().getText();
            String password = passwordDialogFrame.getPasswordField().getText();
            DBHandler.updatingUsersPasswords(mainFrame.getUser(), website, username, password);
            System.out.println(mainFrame.getUser());
            User user = DBHandler.getUser(DBHandler.readDB(), mainFrame.getUser().getUsername());
            System.out.println(user);
            mainFrame.setUser(user);
            mainFrame.updatePasswords();
            JOptionPane.showMessageDialog(passwordDialogFrame, "Password added successfully!");
            passwordDialogFrame.setVisible(false);
            resetFields();
        }
    }

    /**
     * This method resets the fields in PasswordDialogFrame.
     */
    private void resetFields() {
        passwordDialogFrame.getWebsiteField().setText("");
        passwordDialogFrame.getUsernameField().setText("");
        passwordDialogFrame.getPasswordField().setText("");
    }

    /**
     * This method sets the MainFrame.
     * @param mainFrame Jframe
     */
    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    /**
     * This method sets the PasswordDialogFrame.
     * @param passwordDialogFrame Jframe
     */
    public void setPasswordDialogFrame(PasswordDialogFrame passwordDialogFrame) {
        this.passwordDialogFrame = passwordDialogFrame;
    }
}
