package view;

import controllers.AUXCLS;
import controllers.LoginEvent;
import controllers.LoginListener;
import models.User;
import net.miginfocom.swing.MigLayout;
import controllers.DBHandler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class LoginFrame extends JFrame {

    private JLabel icon;
    private JLabel title;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton logIn;
    private JButton register;
    private ArrayList<User> users;
    private Map<String, String> usersCredentials;
    private LoginListener loginListener;

    public LoginFrame() {
        super("Password Manager Login");
        setSize(new Dimension(MainFrame.WIDTH, MainFrame.HEIGHT));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        Image icon = Toolkit.getDefaultToolkit().getImage("src/main/resources/icon16.png");
        setIconImage(icon);

        initComps();
        layoutComps();
    }

    private void initComps() {
        users = DBHandler.readDB();
        System.out.println(users);
        usersCredentials = DBHandler.readCredentials(users);

        icon = new JLabel();
        icon.setIcon(new ImageIcon("src/main/resources/icon64.png"));
        title = new JLabel("Password Manager");
        title.setFont(new Font("Calibri", Font.BOLD, 30));
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        logIn = new JButton("Log In");
        register = new JButton("Register");
    }

    private void layoutComps() {
        setLayout(new MigLayout("insets 180 50 50 50, center", "", "[]20[][]"));
        add(icon, "split 2, center, span");
        add(title, "center, wrap");
        add(new JLabel("Username: "), "split 2, center");
        add(usernameField, "split 2, center, wrap");
        add(new JLabel("Password: "), "split 2, center, gapafter 10");
        add(passwordField, "split 2, center, wrap");
        add(logIn, "split 2, center, span, gapafter 40");
        add(register);

    }

    public void setLoginListener(LoginListener loginListener) {
        this.loginListener = loginListener;
    }

    public void activateComps() {

        if (loginListener != null) {

            logIn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    // Updating the credentials map
                    usersCredentials = DBHandler.readCredentials(DBHandler.readDB());

                    String username = usernameField.getText();
                    if (username.equals("")) {
                        JOptionPane.showMessageDialog(LoginFrame.this, "Please enter a username!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    String password = AUXCLS.convertPasswordToString(passwordField.getPassword());
                    if (password.equals("")) {
                        JOptionPane.showMessageDialog(LoginFrame.this, "Please enter a password!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (usersCredentials.containsKey(username) && usersCredentials.get(username).equals(password)) {
                        User user = DBHandler.getUser(DBHandler.readDB(), username);
                        loginListener.loginEventOccurred(new LoginEvent(this, user));
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(LoginFrame.this, "Invalid username or password!", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }
            });
        }

        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Register button pressed!");
                new RegisterFrame().setAlwaysOnTop(true);
            }
        });
    }
}
