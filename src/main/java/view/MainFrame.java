package view;

import controllers.LoginEvent;
import controllers.LoginListener;
import models.User;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private LoginFrame loginFrame;
    private User user;
    private JLabel userName;
    private JMenuBar menuBar;


    public MainFrame() {
        super("Password Manager");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(false);
        setResizable(false);
        Image icon = Toolkit.getDefaultToolkit().getImage("src/main/resources/icon16.png");
        setIconImage(icon);

        loginFrame = new LoginFrame();
        activateLoginListener();
    }

    private void initComps() {
        userName = new JLabel("Hello " + user.getName() + "!");
        userName.setFont(new Font("Calibri", Font.BOLD, 30));
        menuBar = activateMenuBar();
        setJMenuBar(menuBar);
    }

    private void layoutComps() {
        setLayout(new MigLayout("insets 50 50 50 50"));
        add(userName);
    }

    private void activateLoginListener() {
        loginFrame.setLoginListener(new LoginListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Action performed");
            }

            @Override
            public void loginEventOccurred(LoginEvent e) {
                user = e.getUser();
                initComps();
                layoutComps();
                setVisible(true);
            }

        });
        loginFrame.activateComps();
    }

    private JMenuBar activateMenuBar() {
        System.out.println("activateMenuBar");
        JMenuBar jMenuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu accountMenu = new JMenu("Account");
        JMenu helpMenu = new JMenu("Help");
        JMenuItem exportItem = new JMenuItem("Export as...");
        exportItem.setIcon(new ImageIcon("src/main/resources/export.png"));
        JMenuItem importItem = new JMenuItem("Import");
        importItem.setIcon(new ImageIcon("src/main/resources/import.png"));
        JMenuItem addItem = new JMenuItem("Add new password");
        addItem.setIcon(new ImageIcon("src/main/resources/lock.png"));
        JMenuItem detailsItem = new JMenuItem("Account details");
        detailsItem.setIcon(new ImageIcon("src/main/resources/file.png"));
        JMenuItem editInfoItem = new JMenuItem("Edit account info");
        editInfoItem.setIcon(new ImageIcon("src/main/resources/resume.png"));
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.setIcon(new ImageIcon("src/main/resources/question.png"));
        JMenuItem customerServiceItem = new JMenuItem("Customer service");
        customerServiceItem.setIcon(new ImageIcon("src/main/resources/help-desk.png"));

        fileMenu.add(exportItem);
        fileMenu.add(importItem);
        fileMenu.add(addItem);
        accountMenu.add(detailsItem);
        accountMenu.add(editInfoItem);
        helpMenu.add(aboutItem);
        helpMenu.add(customerServiceItem);

        jMenuBar.add(fileMenu);
        jMenuBar.add(accountMenu);
        jMenuBar.add(helpMenu);


        return jMenuBar;
    }
}
