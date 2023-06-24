package view;

import controllers.MainActionListener;
import models.User;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.util.Map;

/**
 * This class is used as a container for instances of PasswordPanel class.
 */
public class PasswordsPanel extends JPanel {

    private User user;
    private Map<String, Map<String, String>> passwords;

    public PasswordsPanel() {
        setBorder(BorderFactory.createTitledBorder("My passwords"));
        setLayout(new MigLayout("w 680"));
    }

    /**
     * This method is used to update the panel with the passwords.
     */
    public void updatePasswords() {
        removeAll();
        revalidate();
        repaint();
        for (Map.Entry<String, Map<String, String>> entry : passwords.entrySet()) {
            Map<String, String> innerMap = entry.getValue();

            for (String key : innerMap.keySet()) {
                System.out.println(entry.getKey() + " " + key + " " + innerMap.get(key));
                PasswordPanel pp = new PasswordPanel(entry.getKey(), key, innerMap.get(key));
                add(pp, "wrap");
            }
        }
    }

    /**
     * Setter for the user.
     * @param user the user to be set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Setter for the credentials and web adresses.
     * @param passwords a map containing String web adresses as keys and maps containing String usernames and passwords as values
     */
    public void setPasswords(Map<String, Map<String, String>> passwords) {
        this.passwords = passwords;
    }
}
