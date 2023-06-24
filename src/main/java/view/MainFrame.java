package view;

import controllers.LoginEvent;
import controllers.LoginListener;
import controllers.MainActionListener;
import models.User;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * Main user interface for the Password Manager application.
 */
public class MainFrame extends JFrame {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private LoginFrame loginFrame;
    private User user;
    private JLabel userName;
    private JMenuBar menuBar;
    private PasswordsPanel passwordsPanel;
    private JScrollPane scrollPane;
    private JFileChooser fileChooser;
    private JMenuItem exportItem;
    private JMenuItem addItem;
    private JMenuItem generateItem;
    private JMenuItem detailsItem;
    private JMenuItem aboutItem;
    private JMenuItem customerServiceItem;
    private PasswordDialogFrame passwordDialogFrame;
    private MainActionListener mainActionListener;

    /**
     * Constructor for the main frame.
     */
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

    /**
     * Initializes the components for the main frame.
     */
    private void initComps() {
        userName = new JLabel("Hello " + user.getName() + "!");
        userName.setFont(new Font("Calibri", Font.BOLD, 30));
        menuBar = activateMenuBar();
        setJMenuBar(menuBar);
        passwordsPanel = new PasswordsPanel();
        scrollPane = new JScrollPane(passwordsPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setWheelScrollingEnabled(true);
        fileChooser = new JFileChooser();
        passwordDialogFrame = new PasswordDialogFrame();
        passwordDialogFrame.setVisible(false);
        passwordsPanel.setUser(user);
        passwordsPanel.setPasswords(user.getPasswords());
    }

    /**
     * Configures the layout of the components in the main frame.
     */
    private void layoutComps() {
        setLayout(new MigLayout("insets 30 30 30 30, center"));
        add(userName, "span, wrap, w 90%");
        add(scrollPane, "span, wrap, grow, w 90%, h 80%");
    }

    /**
     * Sets up the login listener for the login frame.
     * When a successful login event occurs, the main frame is initialized and displayed.
     */
    private void activateLoginListener() {
        loginFrame.setLoginListener(new LoginListener() {
            @Override
            public void loginEventOccurred(LoginEvent e) {
                user = e.getUser();
                initComps();
                layoutComps();
                setVisible(true);
                activateComps();
                updatePasswords();
            }

        });
        loginFrame.activateComps();
    }

    /**
     * Activates the menu bar by adding menu items and icons.
     */
    private JMenuBar activateMenuBar() {
        System.out.println("activateMenuBar");
        JMenuBar jMenuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu accountMenu = new JMenu("Account");
        JMenu helpMenu = new JMenu("Help");
        exportItem = new JMenuItem("Export as...");
        exportItem.setIcon(new ImageIcon("src/main/resources/export.png"));
        addItem = new JMenuItem("Add new password");
        addItem.setIcon(new ImageIcon("src/main/resources/lock.png"));
        generateItem = new JMenuItem("Generate Password");
        generateItem.setIcon(new ImageIcon("src/main/resources/password.png"));
        detailsItem = new JMenuItem("Account details");
        detailsItem.setIcon(new ImageIcon("src/main/resources/file.png"));
        aboutItem = new JMenuItem("About");
        aboutItem.setIcon(new ImageIcon("src/main/resources/question.png"));
        customerServiceItem = new JMenuItem("Customer service");
        customerServiceItem.setIcon(new ImageIcon("src/main/resources/help-desk.png"));

        fileMenu.add(exportItem);
        fileMenu.add(addItem);
        fileMenu.add(generateItem);
        accountMenu.add(detailsItem);
        helpMenu.add(aboutItem);
        helpMenu.add(customerServiceItem);

        jMenuBar.add(fileMenu);
        jMenuBar.add(accountMenu);
        jMenuBar.add(helpMenu);


        return jMenuBar;
    }

    /**
     * Activates the components by adding action listeners to buttons and menu items.
     * Defines the behavior of the components when clicked.
     */
    private void activateComps() {

        mainActionListener = new MainActionListener();
        mainActionListener.setMainFrame(this);
        mainActionListener.setPasswordDialogFrame(passwordDialogFrame);
        passwordDialogFrame.setMainActionListener(mainActionListener);
        passwordDialogFrame.activateComps();

        exportItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser = new JFileChooser();
                fileChooser.addChoosableFileFilter(new FileNameExtensionFilter(".txt file", "txt"));
                fileChooser.setAcceptAllFileFilterUsed(true);
                fileChooser.setSelectedFile(new File("passwords.txt"));
                int returnVal = fileChooser.showSaveDialog(MainFrame.this);
                if (returnVal == JFileChooser.APPROVE_OPTION && fileChooser.getSelectedFile().getName().endsWith(".txt")) {
                    save2File(fileChooser.getSelectedFile());
                    JOptionPane.showMessageDialog(MainFrame.this, "File saved", "File saved", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(MainFrame.this, "ERROR: File not saved", "File not saved", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        addItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("ADD ITEM BUTTON PRESSED");
                passwordDialogFrame.setVisible(true);
            }
        });

        generateItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PasswordGenerator();
            }
        });

        detailsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AccountDetailsFrame(user);
            }
        });

        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = "Password Manager v1.0\nCreator: Karlo Hasnek";
                JOptionPane.showMessageDialog(MainFrame.this, message, "About", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        customerServiceItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = "For any questions or problems, please contact us any time.\n\n" +
                        "email:  karlohasnek@outlook.com" +
                        "\nphone: +385 91 234 5678";
                JOptionPane.showMessageDialog(MainFrame.this, message, "Customer service", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    /**
     * Getter for the user.
     * @return user object
     */
    public User getUser() {
        return user;
    }

    /**
     * Updates the passwords panel with the passwords of the user.
     */
    public void updatePasswords() {
        passwordsPanel.setPasswords(user.getPasswords());
        System.out.println("updatePasswords of passwordsPanel");
        passwordsPanel.updatePasswords();
    }

    /**
     * Setter for the user.
     * @param user user object
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Saves the passwords to a file.
     * @param file file to save to
     */
    private void save2File(File file) {
        Map<String, Map<String, String>> passwords = getUser().getPasswords();

        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for (String key : passwords.keySet()) {
                bw.write("website: " + key + "\n");
                for (String key2 : passwords.get(key).keySet()) {
                    bw.write("Username: " + key2 + "\n");
                    bw.write("Password: " + passwords.get(key).get(key2) + "\n");
                    bw.write("---------------------------------------------------\n");
                }
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

