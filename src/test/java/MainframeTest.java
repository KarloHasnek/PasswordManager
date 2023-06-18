import models.User;
import view.MainFrame;

import javax.swing.*;

public class MainframeTest {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });
    }
}
