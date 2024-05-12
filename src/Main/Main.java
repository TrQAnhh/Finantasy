package Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class    Main {
    public static <image> void main(String[] args) {

    // GENERATING WINDOW JFRAME:
        JFrame window = new JFrame("Finantasy");

    // WINDOW SETTINGS:

        // SET WINDOW ICON IMAGE
            ImageIcon imageIcon = new ImageIcon("res/Logo/logo.png");
            window.setIconImage(imageIcon.getImage());

        // GAME PANEL SETTING:
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setResizable(false);

            GamePanel gamePanel = new GamePanel();
            window.add(gamePanel);
            window.pack();

            window.setLocationRelativeTo(null);
            window.setVisible(true);

            gamePanel.startGameThread();

    }
}