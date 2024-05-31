package Main;

import javax.swing.*;

import java.awt.Window;


public class Main {
    public static Window window;

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

            gamePanel.config.loadConfig();
            window.pack();

            window.setLocationRelativeTo(null);
            window.setVisible(true);
            gamePanel.setupGame();
            gamePanel.startGameThread();

    }
}